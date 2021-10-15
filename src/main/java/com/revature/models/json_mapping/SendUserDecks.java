package com.revature.models.json_mapping;

import java.util.LinkedList;
import java.util.List;

import com.revature.exceptions.NoCardException;
import com.revature.models.CardRegistry;
import com.revature.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendUserDecks {
	
	private int id;
	private List<SendCard> heroDeck;
	private List<SendCard> villianDeck;
	
	public SendUserDecks(final User user) {
		this.id = user.getId();
		this.heroDeck = new LinkedList<>();
		this.villianDeck = new LinkedList<>();

		final CardRegistry cardBase = CardRegistry.getInstance();
		
		for(final int i: user.getHeroDeck()) {
			try {
				final SendCard card = SendCard.getInstance(cardBase.getCard(i));
				this.heroDeck.add(card);
			} catch(final NoCardException e) {}
		}
		
		for(final int i: user.getVillianDeck()) {
			try {
				final SendCard card = SendCard.getInstance(cardBase.getCard(i));
				this.villianDeck.add(card);
			} catch(final NoCardException e) {}
		}
	}

}
