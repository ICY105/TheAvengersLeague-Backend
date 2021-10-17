package com.revature.models;

public enum EAbilities {
	Preperation		("Preperation",		"Every turn out of combat, gain +5% combat power, up to 15%."),
	SupportHero		("Support",			"+5% Combat bonus to adjacent Super Heros."),
	SupportVillain	("Support",			"+5% Combat bonus to adjacent Super Villian."),
	Leader			("Leader",			"+1 power each round when on the field."),
	Ranged1			("Ranged 1",		"Can attack enemies 1 space away."),
	Ranged2			("Ranged 2",		"Can attack enemies 2 spaces away."),
	MultiRanged1	("Multi-Ranged 1",	"Attacks all enemies 1 space away."),
	MultiRanged2	("Multi-Ranged 2",	"Attacks all enemies 2 spaces away."),
	Tough	        ("Tough",	        "-25% damage taken from combat."),
	None			("None", 			"No ability needed :)");
	
	private final String name;
	private final String desc;
	
	EAbilities(final String name, final String desc) {
		this.name = name;
		this.desc = desc;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDesc() {
		return this.desc;
	}
}
