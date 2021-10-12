package com.revature.models.json_mapping;

import java.util.List;

import com.revature.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendUserDecks {
	
	public SendUserDecks(final User user) {
		this.id = user.getId();
		this.heroDeck = user.getHeroDeck();
		this.villianDeck = user.getVillianDeck();
	}
	
	private int id;
	private List<Integer> heroDeck;
	private List<Integer> villianDeck;

}
