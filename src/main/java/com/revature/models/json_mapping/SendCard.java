package com.revature.models.json_mapping;

import com.revature.models.Card;
import com.revature.models.CustomGameCard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendCard {	
	
	private int id;
	private int powerCost;
	private String affiliation;
	private String ability;
	private String abilityDesc;
	
	private int combat;
	private int durability;
	private int intelligence;
	private int power;
	private int speed;
	private int strength;
	private String name;
	private String image;
	
	public SendCard(final Card card) {
		this.id = card.getId();
		this.powerCost = card.getPowerCost();
		this.ability = card.getAbility().getName();
		this.abilityDesc = card.getAbility().getDesc();
		this.affiliation = card.getAffiliation().toString();
		
		if(card instanceof CustomGameCard) {
			final CustomGameCard customCard = (CustomGameCard) card;
			this.combat = customCard.getCombat();
			this.durability = customCard.getDurability();
			this.intelligence = customCard.getIntelligence();
			this.power = customCard.getPower();
			this.speed = customCard.getSpeed();
			this.strength = customCard.getStrength();
			
			this.name = customCard.getName();
			this.image = customCard.getImageUrl();
		}
	}

}
