package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	
	private int id;
	private EAbilities ability;
	private EAffiliation affiliation;
	
	private int intelligence;	//reduces impact of stat differences
	private int strength;		//affects base damage
	private int speed;			//affects movement range
	private int durability;		//affects max health
	private int power;			//affects base damage
	private int combat;			//ability to reduce damage
	
	public int getMovement() {
		if(this.speed == 0)
			return 1;
		else
			return this.speed/25 + 1;
	}
	
	public int getPowerCost() {
		return ((this.intelligence + this.strength + this.speed + this.durability + this.power + this.combat)/60) + 1;
	}

}
