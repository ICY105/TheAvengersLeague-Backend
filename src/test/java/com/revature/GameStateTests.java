package com.revature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import com.revature.models.Card;
import com.revature.models.CardRegistry;
import com.revature.models.EAffiliation;
import com.revature.models.GameObject;
import com.revature.models.GameState;
import com.revature.models.User;
import com.revature.models.json_mapping.GameObjectMoves;
import com.revature.models.json_mapping.UserTurn;

//@SpringBootTest
public class GameStateTests {
	
	static CardRegistry cards;
	
	User heroUser;
	User villianUser;
	GameState gameState;
	UserTurn turn;
	
	@BeforeAll
	static void preconstruct() {
		cards = CardRegistry.getInstance();
	}
	
	@BeforeEach
	void construct() {
		this.heroUser = new User("First Hero", "Last Hero", "Hero", "Hero Password", "hero@hero.com");
		this.villianUser = new User("First Villian", "Last Villian", "Villian", "Villian Password", "villian@villian.com");
		this.gameState = new GameState(this.heroUser, this.villianUser);
		
		GameObject hero = null;
		for(final GameObject obj: this.gameState.getGameBoard().values()) {
			if(obj.getAffiliation() == EAffiliation.Hero) {
				hero = obj;
				break;
			}
		}
		
		final GameObjectMoves move = new GameObjectMoves();
		move.setUuid(hero.getUuid());
		move.setX(hero.getX());
		move.setY(hero.getY() + 1);
		
		this.turn = new UserTurn();
		this.turn.setPower(this.gameState.getHeroPower());
		this.turn.setHand(new int[this.gameState.getHeroHand().length]);
		for(int i = 0; i < this.gameState.getHeroHand().length; i++)
			this.turn.getHand()[i] = this.gameState.getHeroHand()[i];
		this.turn.setMoves(new GameObjectMoves[] { move });
	}
	
	@AfterEach
	void destruct() {
		this.heroUser = null;
		this.villianUser = null;
		this.gameState = null;
		this.turn = null;
	}

	@Test
	void setTurn_validBasic() {
		Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("valid"), "Failed to set the turn.");
	}

	@Test
	void setTurn_validDeploy() {
		final GameObjectMoves move = new GameObjectMoves();
		move.setId(this.gameState.getHeroHand()[0]);
		move.setX(6);
		move.setY(1);
		this.turn.setMoves(new GameObjectMoves[]{ move } );

		final Card card = cards.getCard(this.turn.getHand()[0]);
		this.turn.setPower(this.turn.getPower() - card.getPowerCost() );
		this.turn.getHand()[0] = 0;
		
		//Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("valid"), "Failed to set the turn.");
	}

	@Test
	void setTurn_invalidDeployPosition() {
		final GameObjectMoves move = new GameObjectMoves();
		move.setId(this.gameState.getHeroHand()[0]);
		move.setX(6);
		move.setY(0);
		this.turn.setMoves(new GameObjectMoves[]{ move } );

		final Card card = cards.getCard(this.turn.getHand()[0]);
		this.turn.setPower(this.turn.getPower() - card.getPowerCost() );
		this.turn.getHand()[0] = 0;
		
		//Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("an object was moved to a duplicate position"), "Failed to catch duplicate position.");
	}

	@Test
	void setTurn_invalidHandSize() {
		this.turn.setHand(new int[0]);
		Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("incorrect handsize"), "Failed to catch incorrect hand size.");
	}

	@Test
	void setTurn_invalidPowerCost() {
		this.turn.setPower(100);
		Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("power cost is incorrect"), "Failed to catch incorrect power.");
	}

	@Test
	void setTurn_invalidMoveBounds() {
		this.turn.getMoves()[0].setY(-1);
		Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("an object was out of bounds"), "Failed to catch incorrect move.");
	}

	@Test
	void setTurn_invalidMoveRange() {
		this.turn.getMoves()[0].setY(10);
		Assert.isTrue(this.gameState.setTurn(this.heroUser.getId(),this.turn).equals("an object was moved beyond its range"), "Failed to catch incorrect move.");
	}
	
}
