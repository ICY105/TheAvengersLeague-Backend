package com.revature.models.json_mapping;

import java.util.List;
import java.util.Set;

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
		this.cards = user.getCards();
		this.heroDeck = user.getHeroDeck();
		this.villianDeck = user.getVillianDeck();
	}
	
	private int id;
	private Set<Integer> cards;
	private List<Integer> heroDeck;
	private List<Integer> villianDeck;

}
