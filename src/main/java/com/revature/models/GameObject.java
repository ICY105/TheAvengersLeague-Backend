package com.revature.models;

import java.util.UUID;

import lombok.Data;

@Data
public class GameObject {
	
	public GameObject(final int x, final int y) {
		this.x = x;
		this.y = y;
		
		this.uuid = UUID.randomUUID().toString();
		this.affiliation = EAffiliation.Neutral;
	}
	
	public GameObject(final int x, final int y, final EAffiliation affiliation) {
		this.x = x;
		this.y = y;
		
		this.uuid = UUID.randomUUID().toString();
		this.affiliation = affiliation;
	}
	
	protected int x;
	protected int y;
	private String uuid;
	private EAffiliation affiliation;
	
}
