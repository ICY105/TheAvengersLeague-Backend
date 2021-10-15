package com.revature.models;

import java.util.Objects;

public class CustomCard extends Card {
	
	private String name;
	private String imageUrl;
	
	public CustomCard() {
		super();
	}
	public CustomCard(final int id, final EAbilities ability, final EAffiliation affiliation, final int intelligence, final int strength,
			final int speed, final int durability, final int power, final int combat, final String name, final String imageUrl) {
		super(id, ability, affiliation, intelligence, strength, speed, durability, power, combat);
		
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name = name;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setImageUrl(final String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	@Override
	public String toString() {
		return "CustomGameCard [name=" + this.name + ", imageUrl=" + this.imageUrl + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.imageUrl, this.name);
		return result;
	}
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CustomCard other = (CustomCard) obj;
		return Objects.equals(this.imageUrl, other.imageUrl) && Objects.equals(this.name, other.name);
	}
	
}
