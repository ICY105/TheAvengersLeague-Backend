package com.revature.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.revature.models.json_mapping.GameObjectMoves;
import com.revature.models.json_mapping.UserTurn;

import lombok.Data;

@Data
public class GameState {
	
	private static final int HANDSIZE = 3;
	private static final int POWERPERTURN = 3;
	private static final int BOARDSIZE = 11;
	private static final int RADIUS = 6;

	private static final int STARTHEROX = 6;
	private static final int STARTHEROY = 0;
	
	private static final int STARTVILLIANX = 6;
	private static final int STARTVILLIANY = 10;
	
	private final CardRegistry cards = CardRegistry.getInstance();
	
	private int turn;
	private int state;
	final Map<String,GameObject> gameBoard;
	private List<String> events;
	
	private final int hero;
	private int heroPower;
	private int heroUpdate;
	private int[] heroHand;
	private final List<Integer> heroDeck;
	private UserTurn heroTurn;

	private final int villain;
	private int villainPower;
	private int villainUpdate;
	private int[] villainHand;
	private final List<Integer> villainDeck;
	private UserTurn villainTurn;
	
	public GameState(final User heros, final User villians) {
		this.turn = 0;
		this.state = 0;
		this.gameBoard = new HashMap<>();
		this.events = new LinkedList<>();

		this.hero = heros.getId();
		this.heroPower = 3;
		this.heroUpdate = -1;
		this.heroHand = new int[HANDSIZE];
		this.heroDeck = randomizeDeck(heros.getHeroDeck());
		drawCards(this.heroHand, this.heroDeck);
		this.heroTurn = null;
		final GameObject startingHero = new CommanderGameCard(STARTHEROX, STARTHEROY, this.cards.getCard(heros.getHeroDeck()[0]), EAffiliation.Hero);
		this.gameBoard.put(startingHero.getUuid(), startingHero);

		this.villain = villians.getId();
		this.villainPower = 3;
		this.villainPower = -1;
		this.villainHand = new int[HANDSIZE];
		this.villainDeck = randomizeDeck(villians.getVillianDeck());
		drawCards(this.villainHand, this.villainDeck);
		this.villainTurn = null;
		final GameObject startingVillian = new CommanderGameCard(STARTVILLIANX, STARTVILLIANY, this.cards.getCard(villians.getVillianDeck()[0]), EAffiliation.Villain);
		this.gameBoard.put(startingVillian.getUuid(), startingVillian);
	}
	
	private List<Integer> randomizeDeck(final int[] heroDeck) {
		final List<Integer> out = Arrays.stream(heroDeck).boxed().collect(Collectors.toList());
		out.remove(0);
		Collections.shuffle(out);
		return out;
	}
	
	private void drawCards(final int[] hand, final List<Integer> deck) {
		for(int i = 0; i < hand.length; i++) {
			if(hand[i] == 0 && deck.size() > 0) {
				hand[i] = deck.get(0);
				deck.remove(0);
			}
		}
	}

	private String validateTurn(final UserTurn turn, final EAffiliation aff) {
		final int power;
		final int[] hand;
		
		
		if(aff == EAffiliation.Hero) {
			power = this.heroPower;
			hand = this.heroHand;
		} else if(aff == EAffiliation.Villain) {
			power = this.villainPower;
			hand = this.villainHand;
		} else {
			return "neutral affiliation";
		}
		
		//check that hand size is correct
		if(turn.getHand().length != HANDSIZE)
			return "incorrect handsize";
		
		//check affiliations
		for(final GameObjectMoves move: turn.getMoves()) {
			if(move.hasUuid()) {
				if(!this.gameBoard.containsKey(move.getUuid()))
					return "a move object has a UUID that doesn't exist";
				final GameObject obj = this.gameBoard.get(move.getUuid());
				if(obj.getAffiliation() != aff)
					return "a move object has the wrong affiliation";
			}
		}
		
		//check the power cost and deployment is correct
		int powerCost = 0;
		for(int i = 0; i < hand.length; i++) {
			if(turn.getHand()[i] == 0 && hand[i] != 0) {
				powerCost += this.cards.getCard(hand[i]).getPowerCost();
				
				boolean deployed = false;
				for(final GameObjectMoves move: turn.getMoves()) {
					if(move.getId() == hand[i]) {
						deployed = true;
						break;
					}
				}
				if(!deployed)
					return "a card has played, but not put on the board.";
			}
		}
		if(powerCost < 0 || power - powerCost != turn.getPower())
			return "power cost is incorrect";
		
		//check movement
		for(final GameObjectMoves move: turn.getMoves()) {
			if(move.getUuid() != null && this.gameBoard.containsKey(move.getUuid())) {
				final GameObject gameObject = this.gameBoard.get(move.getUuid());
				if(gameObject instanceof GameCard) {
					final GameCard card = (GameCard) gameObject;
					if(!inBounds(move.getX(),move.getY()))
						return "an object was out of bounds";
					if(!inRange(card, move.getX(), move.getY()))
						return "an object was moved beyond its range";
				}
			}
		}
		
		//check duplicates & positions
		final List<GameObject> objects = new LinkedList<>();
		for(final GameObject obj: this.gameBoard.values()) {
			if(obj.getAffiliation() == aff)
				objects.add(obj);
		}
		
		final GameObjectMoves[] moves = turn.getMoves();
		for(int i = 0; i < turn.getMoves().length; i++) {
			if(moves[i].hasUuid()) {
				final GameObject obj = this.gameBoard.get(moves[i].getUuid());
				objects.remove(obj);
			}
			for(int j = i+1; j < turn.getMoves().length; j++) {
				if(moves[i].hasUuid()) {
					if(moves[j].hasUuid() && moves[i].getUuid().equals(moves[j].getUuid()))
						return "two objects have a duplicate UUID";
				}
				if(moves[i].getX() == moves[j].getX() && moves[i].getY() == moves[j].getY())
					return "two objects have a duplicate position";
			}
		}
		
		for(final GameObjectMoves move: moves) {
			for(final GameObject obj: objects) {
				if(move.getX() == obj.getX() && move.getY() == obj.getY())
					return "an object was moved to a duplicate position";
			}
		}
		
		//return true if all tests pass
		return "valid";
	}
	
	
	
	private void nextTurn() {
		
		this.events = new LinkedList<>();
		
		//Apply hero turn
		this.heroPower = this.heroTurn.getPower();
		this.heroHand = this.heroTurn.getHand();
		for(final GameObjectMoves move: this.heroTurn.getMoves()) {
			if(move.hasUuid()) {
				if(this.gameBoard.containsKey(move.getUuid())) {
					final GameObject obj = this.gameBoard.get(move.getUuid());
					obj.setX( move.getX() );
					obj.setY( move.getY() );
				}
			} else {
				final GameCard card = new GameCard(move.getX(), move.getY(), this.cards.getCard(move.getId()), EAffiliation.Hero);
				this.gameBoard.put(card.getUuid(), card);
			}
		}
		
		//Apply Villain turn
		this.villainPower = this.villainTurn.getPower();
		this.villainHand = this.villainTurn.getHand();
		for(final GameObjectMoves move: this.villainTurn.getMoves()) {
			if(move.hasUuid()) {
				if(this.gameBoard.containsKey(move.getUuid())) {
					final GameObject obj = this.gameBoard.get(move.getUuid());
					obj.setX( move.getX() );
					obj.setY( move.getY() );
				}
			} else {
				final GameCard card = new GameCard(move.getX(), move.getY(), this.cards.getCard(move.getId()), EAffiliation.Villain);
				this.gameBoard.put(card.getUuid(), card);
			}
		}
		
		/**
		 * unit updates
		 */
		
		final List<GameObject> heroObjs = new LinkedList<>();
		final List<GameObject> villainObjs = new LinkedList<>();
		final List<GameObject> neutralObjs = new LinkedList<>();
		
		//seperate unit affiliations
		for(final GameObject obj: this.gameBoard.values()) {
			if(obj instanceof GameCard)
				((GameCard) obj).setCombatModifier(0.0f);
			
			if(obj.getAffiliation() == EAffiliation.Hero)
				heroObjs.add(obj);
			if(obj.getAffiliation() == EAffiliation.Villain)
				villainObjs.add(obj);
			if(obj.getAffiliation() == EAffiliation.Neutral)
				neutralObjs.add(obj);
		}
		
		//pre-combat abilities
		for(final GameObject obj: this.gameBoard.values()) {
			if(obj instanceof GameCard) {
				final GameCard card = (GameCard) obj;
				
				switch(card.getCard().getAbility()) {
					case SupportHero:
					case SupportVillain:
						if(card.getAffiliation() == EAffiliation.Hero)
							buffInRange(0.05f, 1, card, heroObjs);
						else
							buffInRange(0.05f, 1, card, villainObjs);
						break;
					case Preperation:
						card.addCombatModifier( Math.max(3, card.getLastCombat() - this.turn) * 0.05f  );
						break;
					case Leader:
						if(card.getAffiliation() == EAffiliation.Hero)
							this.heroPower += 1;
						else
							this.villainPower += 1;
						break;
					default:
						break;
				}
			}
		}
		
		//do the combat
		for(final GameObject heroObj: heroObjs) {
			if(heroObj instanceof GameCard) {
				final GameCard heroCard = (GameCard) heroObj;
				for(final GameObject villainObj: villainObjs) {
					if(villainObj instanceof GameCard) {
						final GameCard villainCard = (GameCard) villainObj;
						if(heroCard.getX() == villainCard.getY() && heroCard.getX() == villainCard.getY()) {
							combat(heroCard, villainCard);
							this.events.add(String.format( "combat:%d,%d,%s,%s", heroCard.getX(), heroCard.getY(), heroCard.getUuid(), villainCard.getUuid()) );
							heroCard.setLastCombat(this.turn);
							villainCard.setLastCombat(this.turn);
						}
					}
				}
			}
		}
		
		//post-combat abilities
		for(final GameObject obj: this.gameBoard.values()) {
			
			if(obj instanceof GameCard) {
				final GameCard card = (GameCard) obj;
				
				List<GameObject> attList;
				if(card.getAffiliation() == EAffiliation.Hero)
					attList = villainObjs;
				else
					attList = heroObjs;
				
				switch(card.getCard().getAbility()) {
					case Ranged1:
						if(card.getLastCombat() != this.turn)
							rangedCombat(1, card, attList, false);
						break;
					case Ranged2:
						if(card.getLastCombat() != this.turn)
							rangedCombat(2, card, attList, false);
						break;
					case MultiRanged1:
						if(card.getLastCombat() != this.turn)
							rangedCombat(1, card, attList, true);
						break;
					case MultiRanged2:
						if(card.getLastCombat() != this.turn)
							rangedCombat(2, card, attList, true);
						break;
					default:
						break;
				}
			}
		}
		
		//remove dead cards
		final List<GameObject> removals = new LinkedList<>();
		for(final GameObject obj: this.gameBoard.values()) {
			if(obj instanceof GameCard) {
				final GameCard card = (GameCard) obj;
				if(card.getHealth() < 0)
					removals.add(card);
			}
		}
		for(final GameObject remove: removals) {
			this.gameBoard.remove(remove.getUuid());
			if(remove instanceof GameCard) {
				final GameCard card = (GameCard) remove;
				final int id = card.getCard().getId();
				if(remove.getAffiliation() == EAffiliation.Hero)
					this.heroDeck.add(id);
				if(remove.getAffiliation() == EAffiliation.Villain)
					this.villainDeck.add(id);
			}
			if(remove instanceof CommanderGameCard) {
				if(remove.getAffiliation() == EAffiliation.Hero)
					this.state = this.villain;
				if(remove.getAffiliation() == EAffiliation.Villain)
					this.state = this.hero;
			}
		}
		
		//next turn
		this.turn += 1;
		
		this.heroTurn = null;
		this.heroPower += POWERPERTURN;
		drawCards(this.heroHand, this.heroDeck);
		
		this.villainTurn = null;
		this.villainPower += POWERPERTURN;
		drawCards(this.villainHand, this.villainDeck);
	}
	
	
	
	private void combat(final GameCard obj1, final GameCard obj2) {
		attack(obj1,obj2);
		attack(obj2,obj1);
	}
	
	private void attack(final GameCard obj1, final GameCard obj2) {
		final Card card1 = obj1.getCard();
		final Card card2 = obj2.getCard();
		
		final double dIntel = card2.getIntelligence() - card1.getIntelligence();
		final double combat = card2.getCombat();
		final double attack = card1.getStrength() + card1.getPower();
		
		// damage = attack ^ ( 1.25 - intel/500)
		//          ---------------------------
		//                (combat + 1) / 20
		double damage = Math.pow(attack, 1.25 - (dIntel/500.0)) / ( (combat + 1.0)/20.0 );
		
		damage *= obj1.getCombatModifier() + 1.0;
		damage /= obj2.getCombatModifier() + 1.0;
		
		if(card2.getAbility() == EAbilities.Tough)
			damage = damage * 0.75;
		
		obj2.setHealth( obj2.getHealth() - (int)damage);
	}	
	
	private void rangedCombat(final int range, final GameCard attCard, final List<GameObject> list, final boolean multi) {
		for(final GameObject obj: list) {
			if(obj instanceof GameCard) {
				final GameCard defCard = (GameCard) obj;
				if(inRangeNotEqual(range, attCard.getX(), attCard.getY(), defCard.getX(), defCard.getY()) ) {
					attack(attCard, defCard);
					this.events.add(String.format( "ranged-combat:%d,%d,%d,%d,%s,%s", attCard.getX(), attCard.getY(), defCard.getX(), defCard.getY(), attCard.getUuid(), defCard.getUuid()));
					if(multi)
						break;
				}
			}
		}
	}
	
	private boolean inRange(final GameCard card, final int x, final int y) {
		final int distance = Math.abs(x - card.getX()) + Math.abs(y - card.getY());
		return distance <= card.getCard().getMovement();
	}
	
	private boolean inRangeNotEqual(final int radius, final int x1, final int y1, final int x2, final int y2) {
		if(x1 == x2 && y1 == y2)
			return false;
		final int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2);
		return distance <= radius;
	}
	
	private boolean inBounds(final int x, final int y) {
		if(x >= 0 && x < BOARDSIZE && y >= 0 && y < BOARDSIZE) {
			final int half = BOARDSIZE/2 + 1;
			if(Math.abs( (x-half) + (y-half) ) <= RADIUS)
				return true;
		}
		return false;
	}

	private void buffInRange(final float amount, final int radius, final GameObject card, final List<GameObject> list) {
		for(final GameObject obj: list) {
			if(obj instanceof GameCard) {
				if(inRangeNotEqual(radius,card.getX(),card.getY(),obj.getX(),obj.getY()))
					((GameCard) obj).addCombatModifier(amount);
			}
		}
	}
	
	
	
	

	public synchronized void update() {
		if(this.heroTurn != null && this.villainTurn != null)
			nextTurn();
	}
	
	public boolean hasWinner() {
		return this.state != 0;
	}
	
	public void setWinner(final int id) {
		if(this.hero == id)
			this.state = id;
		if(this.villain == id)
			this.state = id;
	}
	
	public void setLoser(final int id) {
		if(this.hero == id)
			this.state = this.villain;
		if(this.villain == id)
			this.state = this.hero;
	}
	
	public boolean needUpdate(final int id) {
		if(id == this.hero) {
			if(this.heroUpdate == this.turn)
				return false;
			this.heroUpdate = this.turn;
			return true;
		}
		if(id == this.villain) {
			if(this.villainUpdate == this.turn)
				return false;
			this.villainUpdate = this.turn;
			return true;
		}
		return false;
	}
	
	public String setTurn(final int id, final UserTurn moves) {
		if(id == this.hero && this.heroTurn == null) {
			final String valid = validateTurn(moves, EAffiliation.Hero);
			if(valid.equals("valid"))
				this.heroTurn = moves;
			return valid;
		}
		if(id == this.villain && this.villainTurn == null) {
			final String valid = validateTurn(moves, EAffiliation.Villain);
			if(valid.equals("valid"))
				this.villainTurn = moves;
			return valid;
		}
		return "already confirmed the next turn";
	}
	
}
