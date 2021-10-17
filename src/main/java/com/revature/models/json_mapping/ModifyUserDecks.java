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
		if(this.heroDeck != null && this.heroDeck.length >= User.MINDECKSIZE && this.heroDeck.length <= User.MAXDECKSIZE) {
			for(int i = 0; i < this.heroDeck.length; i++) {
				if(this.heroDeck[i] == 0) {
					return false;
				}
				if(this.heroDeck[i] > 0) {
					for(int j = i+1; j < this.heroDeck.length; j++) {
						if(this.heroDeck[i] > 0 && this.heroDeck[i] == this.heroDeck[j]) {
							return false;
						}
					}
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean validVillianDeck() {
		if(this.villianDeck != null && this.villianDeck.length >= User.MINDECKSIZE && this.villianDeck.length <= User.MAXDECKSIZE) {
			for(int i = 0; i < this.villianDeck.length; i++) {
				if(this.villianDeck[i] == 0)
					return false;
				if(this.villianDeck[i] > 0) {
					for(int j = i+1; j < this.villianDeck.length; j++) {
						if(this.villianDeck[i] > 0 && this.villianDeck[i] == this.villianDeck[j])
							return false;
					}
				}
			}
			return true;
		}
		return false;
	}

}
