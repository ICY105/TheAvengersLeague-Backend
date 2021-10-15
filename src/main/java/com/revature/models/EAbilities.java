package com.revature.models;

public enum EAbilities {
	Preperation("Preperation","Every turn out of combat, gain +5% combat power, up to 15%."),
	SupportHero("Support","+5% Combat bonus to adjacent Super Heros."),
	SupportVillain("Support","+5% Combat bonus to adjacent Super Villian.");
	
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
