package com.revature.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.revature.models.json_mapping.GameObjectMoves;
import com.revature.models.json_mapping.UserTurn;

import lombok.Data;

@Data
public class GameState {
	
	private static final int HANDSIZE = 3;
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
	
	private final int heros;
	private final int heroPower;
	private final int[] heroHand;
	private final List<Integer> heroDeck;
	private UserTurn heroTurn;

	private final int villians;
	private final int villianPower;
	private final int[] villianHand;
	private final List<Integer> villianDeck;
	private UserTurn villianTurn;
	
	public GameState(final User heros, final User villians) {
		this.turn = 0;
		this.state = 0;
		this.gameBoard = new HashMap<>();

		this.heros = heros.getId();
		this.heroPower = 3;
		this.heroHand = new int[HANDSIZE];
		this.heroDeck = randomizeDeck(heros.getHeroDeck());
		drawCards(this.heroHand, this.heroDeck);
		this.heroTurn = null;
		final GameObject startingHero = new GameCard(STARTHEROX, STARTHEROY, this.cards.getCard(heros.getHeroDeck()[0]), EAffiliation.Neutral);
		this.gameBoard.put(startingHero.getUuid(), startingHero);

		this.villians = villians.getId();
		this.villianPower = 3;
		this.villianHand = new int[HANDSIZE];
		this.villianDeck = randomizeDeck(villians.getVillianDeck());
		drawCards(this.villianHand, this.villianDeck);
		this.villianTurn = null;
		final GameObject startingVillian = new GameCard(STARTVILLIANX, STARTVILLIANY, this.cards.getCard(villians.getVillianDeck()[0]), EAffiliation.Neutral);
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

	private boolean validateTurn(final UserTurn turn, final EAffiliation aff) {
		final int power;
		final int[] hand;
		
		if(aff == EAffiliation.Hero) {
			power = this.heroPower;
			hand = this.heroHand;
		} else if(aff == EAffiliation.Villian) {
			power = this.villianPower;
			hand = this.villianHand;
		} else {
			return false;
		}
		
		//check that hand size is correct
		if(hand.length != HANDSIZE)
			return false;
		
		//check the power cost and deployment is correct
		int powerCost = 0;
		for(int i = 0; i < hand.length; i++) {
			if(turn.getHand()[i] == 0) {
				powerCost += this.cards.getCard(hand[i]).getPowerCost();
				
				boolean deployed = false;
				for(final GameObjectMoves move: turn.getMoves()) {
					if(move.getId() == hand[i]) {
						deployed = true;
						break;
					}
				}
				if(!deployed)
					return false;
			}
		}
		if(powerCost < 0 || power - powerCost != turn.getPower())
			return false;
		
		//check movement
		for(final GameObjectMoves move: turn.getMoves()) {
			if(move.getUuid() != null && this.gameBoard.containsKey(move.getUuid())) {
				final GameObject gameObject = this.gameBoard.get(move.getUuid());
				if(gameObject instanceof GameCard) {
					final GameCard card = (GameCard) gameObject;
					if(card.getAffiliation() == aff) {
						if(!inBounds(move.getX(),move.getY()))
							return false;
						if(!inRange(card, move.getX(), move.getY()))
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean inBounds(final int x, final int y) {
		if(x >= 0 && x < BOARDSIZE && y >= 0 && y < BOARDSIZE) {
			final int half = BOARDSIZE/2 + 1;
			if(Math.abs( (x-half) + (y-half) ) <= RADIUS)
				return true;
		}
		return false;
	}
	
	private boolean inRange(final GameCard card, final int x, final int y) {
		final int distance = Math.abs(x - card.getX()) + Math.abs(y - card.getY());
		return distance <= card.getCard().getMovement();
	}
	
	private void nextTurn() {
		this.turn += 1;
		this.heroTurn = null;
		this.villianTurn = null;
	}
	
	

	public synchronized void update() {
		if(this.heroTurn != null && this.villianTurn != null)
			nextTurn();
	}
	
	public boolean hasWinner() {
		return this.state != 0;
	}
	
	public void setWinner(final int id) {
		if(this.heros == id)
			this.state = id;
		if(this.villians == id)
			this.state = id;
	}
	
	public void setLoser(final int id) {
		if(this.heros == id)
			this.state = this.villians;
		if(this.villians == id)
			this.state = this.heros;
	}
	
	public boolean setTurn(final int id, final UserTurn moves) {
		if(id == this.heros && this.heroTurn == null) {
			if(validateTurn(moves, EAffiliation.Hero)) {
				this.heroTurn = moves;
				return true;
			}
		}
		if(id == this.villians && this.villianTurn == null) {
			if(validateTurn(moves, EAffiliation.Villian)) {
				this.villianTurn = moves;
				return true;
			}
		}
		return false;
	}
	
}
