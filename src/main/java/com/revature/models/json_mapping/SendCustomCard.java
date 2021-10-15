package com.revature.models.json_mapping;

import com.revature.models.Card;
import com.revature.models.CustomCard;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SendCustomCard extends SendCard {
	
	private int combat;
	private int durability;
	private int intelligence;
	private int power;
	private int speed;
	private int strength;
	private String name;
	private String image;
	
	public SendCustomCard(final Card card) {
		super(card);
		
		if(card instanceof CustomCard) {
			final CustomCard customCard = (CustomCard) card;
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
