package com.revature.models;

import java.util.HashMap;
import java.util.Map;

import com.revature.exceptions.NoCardException;

public class CardRegistry {
	
	private static final CardRegistry cardBase = new CardRegistry();;
	
	public static CardRegistry getInstance() {
		return cardBase;
	}
	
	private final Map<Integer,Card> cards;
	
	public CardRegistry() {
		this.cards = new HashMap<>();
		initCards();
	}
	
	public Card getCard(final int id) {
		if(id == 0)
			return null;
		if(this.cards.containsKey(id))
			return this.cards.get(id);
		else
			throw new NoCardException("id: " + id);
	}
	
	private void initCards() {
		this.cards.put(-1, new CustomGameCard(-1, 3, EAbilities.SupportHero, EAffiliation.Hero, 25, 25, 25, 25, 25, 25, "Police Officer",""));
		this.cards.put(-2, new CustomGameCard(-2, 3, EAbilities.SupportVillain, EAffiliation.Villain, 25, 25, 25, 25, 25, 25, "Henceman",""));
		this.cards.put(70, new Card(70, 3, EAbilities.Preperation, EAffiliation.Hero, 100, 26, 27, 50, 47, 100));
	}
}
