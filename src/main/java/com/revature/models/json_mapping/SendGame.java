package com.revature.models.json_mapping;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.revature.models.Card;
import com.revature.models.CardRegistry;
import com.revature.models.GameObject;
import com.revature.models.GameState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendGame {
	
	private int turn;
	private int state;
	private List<SendGameObject> gameBoard;
	
	private int power;
	private SendCard[] hand;
	
	public SendGame(final int userId, final GameState game) {
		this.turn = game.getTurn();
		this.state = game.getState();
		this.gameBoard = new LinkedList<SendGameObject>();
		for(final Map.Entry<String, GameObject> entry: game.getGameBoard().entrySet()) {
			this.gameBoard.add( new SendGameObject(entry.getValue()));
		}
		
		if(userId == game.getHeros())
			initHero(game);
		else if(userId == game.getVillians())
			initVillian(game);
	}

	private void initHero(final GameState game) {
		final CardRegistry cards = CardRegistry.getInstance();
		this.power = game.getHeroPower();
		this.hand = new SendCard[game.getHeroHand().length];
		for(int i = 0; i < game.getHeroHand().length; i++) {
			final Card card = cards.getCard(game.getHeroHand()[i]);
			if(card != null)
				this.hand[i] = new SendCard(card);
		}
	}

	private void initVillian(final GameState game) {
		final CardRegistry cards = CardRegistry.getInstance();
		this.power = game.getVillianPower();
		this.hand = new SendCard[game.getVillianHand().length];
		for(int i = 0; i < game.getVillianHand().length; i++) {
			final Card card = cards.getCard(game.getVillianHand()[i]);
			if(card != null)
				this.hand[i] = new SendCard(card);
		}
	}
	
}
