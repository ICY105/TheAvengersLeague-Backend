package com.revature.models.json_mapping;

import com.revature.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserDecks {
	
	private int[] heroDeck;
	private int[] villianDeck;
	
	public boolean validHeroDeck() {
		return this.heroDeck != null && this.heroDeck.length >= User.MINDECKSIZE && this.heroDeck.length <= User.MAXDECKSIZE;
	}
	
	public boolean validVillianDeck() {
		return this.villianDeck != null && this.villianDeck.length >= User.MINDECKSIZE && this.villianDeck.length <= User.MAXDECKSIZE;
	}

}
