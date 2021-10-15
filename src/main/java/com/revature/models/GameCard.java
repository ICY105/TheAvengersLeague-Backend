package com.revature.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class GameCard extends GameObject {

	public GameCard(final int x, final int y, final Card card, final EAffiliation affiliation) {
		super(x, y, affiliation);
		this.card = card;
		
		this.health = card.getDurability();
	}
	
	private Card card;
	private int health;
	private float combatModifier = 0.0f;
	private int lastCombat = 0;
	
	public void addCombatModifier(final float amount) {
		this.combatModifier += amount;
	}

}
