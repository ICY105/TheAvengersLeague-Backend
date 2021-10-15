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
		this.cards.put(-1, new CustomGameCard(-1, EAbilities.SupportHero,    EAffiliation.Hero,    18, 18, 18, 18, 18, 18, "Police Officer","https://www.pngfind.com/pngs/m/66-665071_police-officer-police-officer-png-transparent-png.png"));
		this.cards.put(-2, new CustomGameCard(-2, EAbilities.SupportVillain, EAffiliation.Villain, 18, 18, 18, 18, 18, 18, "Henceman","http://player.98fm.com/content/000/images/000095/97723_54_news_hub_93274_656x500.jpg"));
		
		this.cards.put(70,  new Card(70,  EAbilities.Preperation, EAffiliation.Hero, 100, 26,  27,  50,  47,  100));
		this.cards.put(149, new Card(149, EAbilities.Leader,      EAffiliation.Hero, 69,  19,  38,  55,  60,  100));
		this.cards.put(346, new Card(346, EAbilities.Ranged1,     EAffiliation.Hero, 100, 85,  58,  85,  100, 64 ));
		this.cards.put(644, new Card(644, EAbilities.Tough,       EAffiliation.Hero, 94,  100, 100, 100, 100, 85 ));
		
		this.cards.put(370, new Card(370, EAbilities.Preperation, EAffiliation.Villain, 100,  10,  12,  60,  43, 70 ));
		this.cards.put(204, new Card(204, EAbilities.Tough,       EAffiliation.Villain,  88, 100,  83, 100, 100, 95 ));
		this.cards.put(680, new Card(680, EAbilities.Ranged1,     EAffiliation.Villain,  88,  83,  42, 100, 100, 64 ));
		this.cards.put(423, new Card(423, EAbilities.Leader,      EAffiliation.Villain,  88,  80,  27,  84, 91,  80 ));
	}
}
