package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	private int id;
	private int powerCost;
	private EAbilities ability;
	
	private int intelligence;
	private int strength;
	private int speed;
	private int durability;
	private int power;
	private int combat;
	
	public int getMovement() {
		if(this.speed == 0)
			return 1;
		else
			return this.speed/25 + 1;
	}

}
