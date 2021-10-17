package com.revature.models;

import java.util.HashMap;
import java.util.Map;

import com.revature.exceptions.NoCardException;

public class CardRegistry {
	
	private static final CardRegistry CustomCardBase = new CardRegistry();;
	
	public static CardRegistry getInstance() {
		return CustomCardBase;
	}
	
	private final Map<Integer,CustomCard> cards;
	
	public CardRegistry() {
		this.cards = new HashMap<>();
		initCards();
	}
	
	public Card getCustomCard(final int id) {
		if(id == 0)
			return null;
		if(this.cards.containsKey(id))
			return this.cards.get(id);
		else
			throw new NoCardException("id: " + id);
	}
	
	private void initCards() {
		this.cards.put(-1, new CustomCard(-1, EAbilities.SupportHero,    EAffiliation.Hero,    18, 18, 18, 18, 18, 18, "Police Officer","https://www.pngfind.com/pngs/m/66-665071_police-officer-police-officer-png-transparent-png.png"));
		this.cards.put(-2, new CustomCard(-2, EAbilities.SupportVillain, EAffiliation.Villain, 18, 18, 18, 18, 18, 18, "Henchman","http://player.98fm.com/content/000/images/000095/97723_54_news_hub_93274_656x500.jpg"));
		// Heroes
		this.cards.put(30,  new CustomCard(30,  EAbilities.None,        EAffiliation.Hero, 75,  18,  17,  40,  53,  30,  "Ant-Man II",         "https://www.superherodb.com/pictures2/portraits/10/100/166.jpg"));
		this.cards.put(38,  new CustomCard(38,  EAbilities.None,        EAffiliation.Hero, 81,  85,  79,  80 , 100, 80,  "Aquaman",            "https://www.superherodb.com/pictures2/portraits/10/100/634.jpg"));
		this.cards.put(70,  new CustomCard(70,  EAbilities.Preperation, EAffiliation.Hero, 100, 26,  27,  50,  47,  100, "Batman",             "https://www.superherodb.com/pictures2/portraits/10/100/639.jpg"));
		this.cards.put(106, new CustomCard(106, EAbilities.None,        EAffiliation.Hero, 88,  16,  30,  60,  41,  100, "Black Panther",      "https://www.superherodb.com/pictures2/portraits/10/100/247.jpg"));
		this.cards.put(107, new CustomCard(107, EAbilities.None,        EAffiliation.Hero, 75,  13,  33,  30,  36,  100, "Black Widow",        "https://www.superherodb.com/pictures2/portraits/10/100/248.jpg"));
		this.cards.put(149, new CustomCard(149, EAbilities.Leader,      EAffiliation.Hero, 69,  19,  38,  55,  60,  100, "Captain America",    "https://www.superherodb.com/pictures2/portraits/10/100/274.jpg"));
		this.cards.put(157, new CustomCard(157, EAbilities.None,        EAffiliation.Hero, 84,  88,  71,  95,  100, 90, "Captain Marvel",      "https://www.superherodb.com/pictures2/portraits/10/100/103.jpg"));
		this.cards.put(194, new CustomCard(194, EAbilities.Ranged1,	    EAffiliation.Hero, 75,  53,  42,  85,  71,  64,  "Cyborg",             "https://www.superherodb.com/pictures2/portraits/10/100/1204.jpg"));
		this.cards.put(196, new CustomCard(196, EAbilities.Ranged1,	    EAffiliation.Hero, 75,  10,  23,  42,  76,  80, "Cyclops",             "https://www.superherodb.com/pictures2/portraits/10/100/50.jpg"));
		this.cards.put(226, new CustomCard(226, EAbilities.None,        EAffiliation.Hero, 100, 10,  12,  84,  100, 60,  "Doctor Strange",     "https://www.superherodb.com/pictures2/portraits/10/100/55.jpg"));
		this.cards.put(234, new CustomCard(234, EAbilities.None,        EAffiliation.Hero, 56,  80,  25,  85,  46,  65,  "Drax the Destroyer", "https://www.superherodb.com/pictures2/portraits/10/100/10016.jpg"));
		this.cards.put(251, new CustomCard(251, EAbilities.None,        EAffiliation.Hero, 38,  13,  50,  28,  22,  64,  "Falcon",             "https://www.superherodb.com/pictures2/portraits/10/100/56.jpg"));
		this.cards.put(265, new CustomCard(234, EAbilities.None,        EAffiliation.Hero, 88,  48,  100, 60,  100, 60,  "Flash II",           "https://www.superherodb.com/pictures2/portraits/10/100/892.jpg"));
		this.cards.put(275, new CustomCard(275, EAbilities.None,        EAffiliation.Hero, 75,  85,  42,  85,  53,  100, "Gamora",             "https://www.superherodb.com/pictures2/portraits/10/100/65.jpg"));
		this.cards.put(303, new CustomCard(303, EAbilities.None,        EAffiliation.Hero, 75,  85,  33,  70,  100, 64,  "Groot",              "https://www.superherodb.com/pictures2/portraits/10/100/10017.jpg"));
		this.cards.put(313, new CustomCard(313, EAbilities.Ranged2,	    EAffiliation.Hero, 56,  12,  21,  10,  29,  80,  "Hawkeye",            "https://www.superherodb.com/pictures2/portraits/10/100/73.jpg"));
		this.cards.put(332, new CustomCard(332, EAbilities.Tough,       EAffiliation.Hero, 88,  100, 63,  100, 98,  85,  "Hulk",               "https://www.superherodb.com/pictures2/portraits/10/100/83.jpg"));
		this.cards.put(346, new CustomCard(346, EAbilities.Ranged1,     EAffiliation.Hero, 100, 85,  58,  85,  100, 64,  "Iron Man",           "https://www.superherodb.com/pictures2/portraits/10/100/85.jpg"));
		this.cards.put(489, new CustomCard(489, EAbilities.Leader,      EAffiliation.Hero, 75,  11,  23,  42,  25,  100, "Nick Fury",          "https://www.superherodb.com/pictures2/portraits/10/100/326.jpg"));
		this.cards.put(566, new CustomCard(566, EAbilities.Ranged2,	    EAffiliation.Hero, 50,  5,   23,  28,  28,  64, "Rocket Raccoon",      "https://www.superherodb.com/pictures2/portraits/10/100/10010.jpg"));
		this.cards.put(579, new CustomCard(579, EAbilities.Ranged1,     EAffiliation.Hero, 100, 10,  29,  70,  100, 80,  "Scarlet Witch",      "https://www.superherodb.com/pictures2/portraits/10/100/444.jpg"));
		this.cards.put(620, new CustomCard(620, EAbilities.None,        EAffiliation.Hero, 90,  55,  67,  75,  74,  85,  "Spider-Man",         "https://www.superherodb.com/pictures2/portraits/10/100/133.jpg"));
		this.cards.put(630, new CustomCard(630, EAbilities.MultiRanged1,EAffiliation.Hero, 69,  20,  33,  50,  25,  70,  "Star-Lord",          "https://www.superherodb.com/pictures2/portraits/10/100/10015.jpg"));
		this.cards.put(644, new CustomCard(644, EAbilities.Tough,       EAffiliation.Hero, 94,  100, 100, 100, 100, 85,  "Superman",           "https://www.superherodb.com/pictures2/portraits/10/100/791.jpg"));
		this.cards.put(659, new CustomCard(659, EAbilities.Tough,       EAffiliation.Hero, 69,  100, 83,  100, 100, 100, "Thor",               "https://www.superherodb.com/pictures2/portraits/10/100/140.jpg"));
		this.cards.put(697, new CustomCard(697, EAbilities.Ranged2,	    EAffiliation.Hero, 100, 72,  54,  95,  100, 70,  "Vision",             "https://www.superherodb.com/pictures2/portraits/10/100/532.jpg"));
		this.cards.put(703, new CustomCard(703, EAbilities.Ranged1,	    EAffiliation.Hero, 63,  80,  63,  100, 100, 85,  "War Machine",        "https://www.superherodb.com/pictures2/portraits/10/100/536.jpg"));
		this.cards.put(714, new CustomCard(714, EAbilities.None,        EAffiliation.Hero, 56,  31,  35,  65,  60,  84,  "Winter Soldier",     "https://www.superherodb.com/pictures2/portraits/10/100/10027.jpg"));
		this.cards.put(717, new CustomCard(717, EAbilities.Tough,       EAffiliation.Hero, 63,  32,  50,  100, 89,  100, "Wolverine",          "https://www.superherodb.com/pictures2/portraits/10/100/161.jpg"));
		this.cards.put(720, new CustomCard(720, EAbilities.Leader,      EAffiliation.Hero, 88,  100, 79,  100, 100, 100, "Wonder Woman",       "https://www.superherodb.com/pictures2/portraits/10/100/807.jpg"));
		// Villains
		this.cards.put(60,  new CustomCard(60,  EAbilities.Preperation, EAffiliation.Villain, 88,  38,  23,  56,  51,  95,  "Bane",           "https://www.superherodb.com/pictures2/portraits/10/100/637.jpg"));
		this.cards.put(204, new CustomCard(204, EAbilities.Tough,       EAffiliation.Villain, 88,  100, 83,  100, 100, 95,  "Darkseid",       "https://www.superherodb.com/pictures2/portraits/10/100/668.jpg"));
		this.cards.put(214, new CustomCard(214, EAbilities.Ranged2,     EAffiliation.Villain, 50,  10,  23,  28,  55,  80,  "Deadshot",       "https://www.superherodb.com/pictures2/portraits/10/100/670.jpg"));
		this.cards.put(216, new CustomCard(216, EAbilities.None,        EAffiliation.Villain, 75,  30,  35,  100, 47,  90,  "Deathstroke",    "https://www.superherodb.com/pictures2/portraits/10/100/672.jpg"));
		this.cards.put(222, new CustomCard(222, EAbilities.Ranged1,     EAffiliation.Villain, 100, 32,  20,  100, 100, 84,  "Doctor Doom",    "https://www.superherodb.com/pictures2/portraits/10/100/189.jpg"));
		this.cards.put(225, new CustomCard(225, EAbilities.Ranged1,	    EAffiliation.Villain, 94,  48,  33,  40,  53,  65,  "Doctor Octopus", "https://www.superherodb.com/pictures2/portraits/10/100/622.jpg"));
		this.cards.put(232, new CustomCard(232, EAbilities.Tough,       EAffiliation.Villain, 88,  95,  83,  100, 100, 80,  "Dormammu",       "https://www.superherodb.com/pictures2/portraits/10/100/1359.jpg"));
		this.cards.put(237, new CustomCard(237, EAbilities.MultiRanged2,EAffiliation.Villain, 69,  10,  50,  56,  67,  64,  "Electro",        "https://www.superherodb.com/pictures2/portraits/10/100/7.jpg"));
		this.cards.put(273, new CustomCard(273, EAbilities.Tough,       EAffiliation.Villain, 100, 100, 83,  100, 100, 50,  "Galactus",       "https://www.superherodb.com/pictures2/portraits/10/100/862.jpg"));
		this.cards.put(299, new CustomCard(299, EAbilities.MultiRanged1,EAffiliation.Villain, 100, 48,  38,  60,  48,  50,  "Green Goblin",   "https://www.superherodb.com/pictures2/portraits/10/100/579.jpg"));
		this.cards.put(309, new CustomCard(309, EAbilities.None,        EAffiliation.Villain, 88,  12,  33,  65,  55,  80,  "Harley Quinn",   "https://www.superherodb.com/pictures2/portraits/10/100/701.jpg"));
		this.cards.put(347, new CustomCard(347, EAbilities.Ranged1,     EAffiliation.Villain, 88,  63,  25,  90,  57,  56,  "Iron Monger",    "https://www.superherodb.com/pictures2/portraits/10/100/886.jpg"));
		this.cards.put(370, new CustomCard(370, EAbilities.Preperation, EAffiliation.Villain, 100, 10,  12,  60,  43,  70,  "Joker",          "https://www.superherodb.com/pictures2/portraits/10/100/719.jpg"));
		this.cards.put(386, new CustomCard(386, EAbilities.None,        EAffiliation.Villain, 19,  53,  35,  90,  53,  60,  "Killer Croc",    "https://www.superherodb.com/pictures2/portraits/10/100/723.jpg"));
		this.cards.put(405, new CustomCard(405, EAbilities.Preperation, EAffiliation.Villain, 100, 53,  25,  65,  68,  70,  "Lex Luthor",     "https://www.superherodb.com/pictures2/portraits/10/100/727.jpg"));
		this.cards.put(414, new CustomCard(414, EAbilities.None,        EAffiliation.Villain, 88,  63,  46,  85,  100, 60,  "Loki",           "https://www.superherodb.com/pictures2/portraits/10/100/928.jpg"));
		this.cards.put(423, new CustomCard(423, EAbilities.Leader,      EAffiliation.Villain, 88,  80,  27,  84,  91,  80,  "Magneto",        "https://www.superherodb.com/pictures2/portraits/10/100/12.jpg"));
		this.cards.put(479, new CustomCard(479, EAbilities.MultiRanged2,EAffiliation.Villain, 81,  10,  17,  40,  82,  70,  "Mysterio",       "https://www.superherodb.com/pictures2/portraits/10/100/1039.jpg"));
		this.cards.put(514, new CustomCard(514, EAbilities.Preperation, EAffiliation.Villain, 75,  10,  12,  28,  30,  45,  "Penguin",        "https://www.superherodb.com/pictures2/portraits/10/100/753.jpg"));
		this.cards.put(550, new CustomCard(550, EAbilities.Leader,	    EAffiliation.Villain, 75,  10,  12,  14,  19,  80,  "Red Skull",      "https://www.superherodb.com/pictures2/portraits/10/100/1392.jpg"));
		this.cards.put(554, new CustomCard(554, EAbilities.Tough,       EAffiliation.Villain, 25,  80,  43,  90,  36,  85,  "Rhino",          "https://www.superherodb.com/pictures2/portraits/10/100/1151.jpg"));
		this.cards.put(572, new CustomCard(572, EAbilities.MultiRanged1,EAffiliation.Villain, 44,  75,  46,  90,  100, 60,  "Sandman",        "https://www.superherodb.com/pictures2/portraits/10/100/621.jpg"));
		this.cards.put(655, new CustomCard(655, EAbilities.Tough,       EAffiliation.Villain, 100, 100, 33,  100, 100, 80,  "Thanos",         "https://www.superherodb.com/pictures2/portraits/10/100/1305.jpg"));
		this.cards.put(678, new CustomCard(678, EAbilities.Leader,      EAffiliation.Villain, 88,  10,  12,  14,  9,   28,  "Two-Face",       "https://www.superherodb.com/pictures2/portraits/10/100/802.jpg"));
		this.cards.put(680, new CustomCard(680, EAbilities.Ranged1,     EAffiliation.Villain, 88,  83,  42,  100, 100, 64,  "Ultron",         "https://www.superherodb.com/pictures2/portraits/10/100/1339.jpg"));
	}
}
