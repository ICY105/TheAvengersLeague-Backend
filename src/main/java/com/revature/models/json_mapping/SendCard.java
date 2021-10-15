package com.revature.models.json_mapping;

import com.revature.models.Card;
import com.revature.models.CustomCard;

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
	
	protected SendCard(final Card card) {
		this.id = card.getId();
		this.powerCost = card.getPowerCost();
		this.ability = card.getAbility().getName();
		this.abilityDesc = card.getAbility().getDesc();
		this.affiliation = card.getAffiliation().toString();
	}
	
	public static SendCard getInstance(final Card card) {
		if(card instanceof CustomCard) {
			return new SendCustomCard(card);
		} else {
			return new SendCard(card);
		}
	}

}
