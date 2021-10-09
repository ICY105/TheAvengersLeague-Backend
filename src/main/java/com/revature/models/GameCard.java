package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameCard {
	
	private int id;
	private int powerCost;
	private String ability;
	private String abilityDesc;

}
