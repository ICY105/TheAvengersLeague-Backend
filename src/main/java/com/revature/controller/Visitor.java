package com.revature.controller;

import com.revature.models.User;

public class Visitor {
	
	private int userId;
	private User user;
	
	public Visitor() {
		this.userId = -1;
		this.user = null;
	}

	public int getUserId() {
		return this.userId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUserId(final int userId) {
		this.userId = userId;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
