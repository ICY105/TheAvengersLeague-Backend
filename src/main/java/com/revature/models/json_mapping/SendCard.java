package com.revature.models.json_mapping;

import com.revature.models.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendCard {	
	
	private int id;
	private int powerCost;
	private String ability;
	private String abilityDesc;
	
	public SendCard(final Card card) {
		this.id = card.getId();
		this.powerCost = card.getPowerCost();
		this.ability = card.getAbility().getName();
		this.abilityDesc = card.getAbility().getDesc();
	}

}
