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
	
	public Map<Integer,Card> getAllCards() {
		return this.cards;
	}
	
	private void initCards() {
		this.cards.put(-1, new CustomCard(-1, EAbilities.SupportHero,    EAffiliation.Hero,    18, 18, 18, 18, 18, 18, "Police Officer","https://www.pngfind.com/pngs/m/66-665071_police-officer-police-officer-png-transparent-png.png"));
		this.cards.put(-2, new CustomCard(-2, EAbilities.SupportVillain, EAffiliation.Villain, 18, 18, 18, 18, 18, 18, "Henchman","http://player.98fm.com/content/000/images/000095/97723_54_news_hub_93274_656x500.jpg"));
		// Heroes
		this.cards.put(30, 	new Card(30,  EAbilities.None,		  EAffiliation.Hero, 75,  18,  17,  40,  53,  30 )); // Ant-Man II
		this.cards.put(38,  new Card(38,  EAbilities.None,		  EAffiliation.Hero, 81,  85,  79,  80 , 100, 80 )); // Aquaman
		this.cards.put(70,  new Card(70,  EAbilities.Preperation, EAffiliation.Hero, 100, 26,  27,  50,  47,  100)); // Batman
		this.cards.put(106, new Card(106, EAbilities.None,		  EAffiliation.Hero, 88,  16,  30,  60,  41,  100)); // Black Panther
		this.cards.put(107, new Card(107, EAbilities.None,		  EAffiliation.Hero, 75,  13,  33,  30,  36,  100)); // Black Widow
		this.cards.put(149, new Card(149, EAbilities.Leader,      EAffiliation.Hero, 69,  19,  38,  55,  60,  100)); // Captain America
		this.cards.put(157, new Card(157, EAbilities.None,		  EAffiliation.Hero, 84,  88,  71,  95,  100, 90 )); // Captain Marvel
		this.cards.put(194, new Card(194, EAbilities.Ranged1,	  EAffiliation.Hero, 75,  53,  42,  85,  71,  64 )); // Cyborg
		this.cards.put(196, new Card(196, EAbilities.Ranged1,	  EAffiliation.Hero, 75,  10,  23,  42,  76,  80 )); // Cyclops
		this.cards.put(226, new Card(226, EAbilities.None,		  EAffiliation.Hero, 100, 10,  12,  84,  100, 60 )); // Doctor Strange
		this.cards.put(234, new Card(234, EAbilities.None,		  EAffiliation.Hero, 56,  80,  25,  85,  46,  65 )); // Drax the Destroyer
		this.cards.put(251, new Card(251, EAbilities.None,		  EAffiliation.Hero, 38,  13,  50,  28,  22,  64 )); // Falcon
		this.cards.put(265, new Card(234, EAbilities.None,		  EAffiliation.Hero, 88,  48,  100, 60,  100, 60 )); // Flash II
		this.cards.put(275, new Card(275, EAbilities.None,		  EAffiliation.Hero, 75,  85,  42,  85,  53,  100)); // Gamora
		this.cards.put(303, new Card(303, EAbilities.None,		  EAffiliation.Hero, 75,  85,  33,  70,  100, 64 )); // Groot
		this.cards.put(313, new Card(313, EAbilities.Ranged2,	  EAffiliation.Hero, 56,  12,  21,  10,  29,  80 )); // Hawkeye
		this.cards.put(332, new Card(332, EAbilities.Tough,		  EAffiliation.Hero, 88,  100, 63,  100, 98,  85 )); // Hulk
		this.cards.put(346, new Card(346, EAbilities.Ranged1,     EAffiliation.Hero, 100, 85,  58,  85,  100, 64 )); // Iron Man
		this.cards.put(489, new Card(489, EAbilities.Leader,	  EAffiliation.Hero, 75,  11,  23,  42,  25,  100)); // Nick Fury
		this.cards.put(566, new Card(566, EAbilities.Ranged2,	  EAffiliation.Hero, 50,  5,   23,  28,  28,  64 )); // Rocket Raccoon
		this.cards.put(579, new Card(579, EAbilities.Ranged1,	  EAffiliation.Hero, 100, 10,  29,  70,  100, 80 )); // Scarlet Witch
		this.cards.put(620, new Card(620, EAbilities.None,		  EAffiliation.Hero, 90,  55,  67,  75,  74,  85 )); // Spider-Man
		this.cards.put(630, new Card(630, EAbilities.MultiRanged1,EAffiliation.Hero, 69,  20,  33,  50,  25,  70 )); // Star-Lord
		this.cards.put(644, new Card(644, EAbilities.Tough,       EAffiliation.Hero, 94,  100, 100, 100, 100, 85 )); // Superman
		this.cards.put(659, new Card(659, EAbilities.Tough,		  EAffiliation.Hero, 69,  100, 83,  100, 100, 100)); // Thor
		this.cards.put(697, new Card(697, EAbilities.Ranged2,	  EAffiliation.Hero, 100, 72,  54,  95,  100, 70 )); // Vision
		this.cards.put(703, new Card(703, EAbilities.Ranged1,	  EAffiliation.Hero, 63,  80,  63,  100, 100, 85 )); // War Machine
		this.cards.put(714, new Card(714, EAbilities.None,	  	  EAffiliation.Hero, 56,  31,  35,  65,  60,  84 )); // Winter Soldier
		this.cards.put(717, new Card(717, EAbilities.Tough,		  EAffiliation.Hero, 63,  32,  50,  100, 89,  100)); // Wolverine
		this.cards.put(720, new Card(720, EAbilities.Leader,	  EAffiliation.Hero, 88,  100, 79,  100, 100, 100)); // Wonder Woman
		// Villains
		this.cards.put(60,  new Card(60,  EAbilities.Preperation, EAffiliation.Villain, 88,  38,  23,  56,  51,  95 )); // Bane
		this.cards.put(204, new Card(204, EAbilities.Tough,       EAffiliation.Villain, 88,  100, 83,  100, 100, 95 )); // Darkseid
		this.cards.put(214, new Card(214, EAbilities.Ranged2,	  EAffiliation.Villain, 50,  10,  23,  28,  55,  80 )); // Deadshot
		this.cards.put(216, new Card(216, EAbilities.None,		  EAffiliation.Villain, 75,  30,  35,  100, 47,  90 )); // Deathstroke
		this.cards.put(222, new Card(222, EAbilities.Ranged1,	  EAffiliation.Villain, 100, 32,  20,  100, 100, 84 )); // Doctor Doom
		this.cards.put(225, new Card(225, EAbilities.Ranged1,	  EAffiliation.Villain, 94,  48,  33,  40,  53,  65 )); // Doctor Octopus
		this.cards.put(232, new Card(232, EAbilities.Tough,		  EAffiliation.Villain, 88,  95,  83,  100, 100, 80 )); // Dormammu
		this.cards.put(237, new Card(237, EAbilities.MultiRanged2,EAffiliation.Villain, 69,  10,  50,  56,  67,  64 )); // Electro
		this.cards.put(273, new Card(273, EAbilities.Tough,		  EAffiliation.Villain, 100, 100, 83,  100, 100, 50 )); // Galactus
		this.cards.put(299, new Card(299, EAbilities.MultiRanged1,EAffiliation.Villain, 100, 48,  38,  60,  48,  50 )); // Green Goblin
		this.cards.put(309, new Card(309, EAbilities.None,		  EAffiliation.Villain, 88,  12,  33,  65,  55,  80 )); // Harley Quinn
		this.cards.put(347, new Card(347, EAbilities.Ranged1,	  EAffiliation.Villain, 88,  63,  25,  90,  57,  56 )); // Iron Monger
		this.cards.put(370, new Card(370, EAbilities.Preperation, EAffiliation.Villain, 100, 10,  12,  60,  43,  70 )); // Joker
		this.cards.put(386, new Card(386, EAbilities.None,		  EAffiliation.Villain, 19,  53,  35,  90,  53,  60 )); // Killer Croc
		this.cards.put(405, new Card(405, EAbilities.Preperation, EAffiliation.Villain, 100, 53,  25,  65,  68,  70 )); // Lex Luthor
		this.cards.put(414, new Card(414, EAbilities.None,		  EAffiliation.Villain, 88,  63,  46,  85,  100, 60 )); // Loki
		this.cards.put(423, new Card(423, EAbilities.Leader,      EAffiliation.Villain, 88,  80,  27,  84,  91,  80 )); // Magneto
		this.cards.put(479, new Card(479, EAbilities.MultiRanged2,EAffiliation.Villain, 81,  10,  17,  40,  82,  70 )); // Mysterio
		this.cards.put(514, new Card(514, EAbilities.Preperation, EAffiliation.Villain, 75,  10,  12,  28,  30,  45 )); // Penguin
		this.cards.put(550, new Card(550, EAbilities.Leader,	  EAffiliation.Villain, 75,  10,  12,  14,  19,  80 )); // Red Skull
		this.cards.put(554, new Card(554, EAbilities.Tough,		  EAffiliation.Villain, 25,  80,  43,  90,  36,  85 )); // Rhino
		this.cards.put(572, new Card(572, EAbilities.MultiRanged1,EAffiliation.Villain, 44,  75,  46,  90,  100, 60 )); // Sandman
		this.cards.put(655, new Card(655, EAbilities.Tough,		  EAffiliation.Villain, 100, 100, 33,  100, 100, 80 )); // Thanos
		this.cards.put(678, new Card(678, EAbilities.Leader,	  EAffiliation.Villain, 88,  10,  12,  14,  9,   28 )); // Two-Face
		this.cards.put(680, new Card(680, EAbilities.Ranged1,     EAffiliation.Villain, 88,  83,  42,  100, 100, 64 )); // Ultron
	}
}
