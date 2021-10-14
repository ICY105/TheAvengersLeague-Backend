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
public class SendUserInventory {
	
	public SendUserInventory(final User user) {
		this.id = user.getId();
		this.cards = new LinkedList<>();

		final CardRegistry cardBase = CardRegistry.getInstance();
		
		for(final int i: user.getCards()) {
			try {
				final SendCard card = new SendCard(cardBase.getCard(i));
				this.cards.add(card);
			} catch(final NoCardException e) {}
		}
	}
	
	private int id;
	private List<SendCard> cards;

}
