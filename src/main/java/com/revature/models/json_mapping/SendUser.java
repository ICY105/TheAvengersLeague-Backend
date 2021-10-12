package com.revature.models.json_mapping;

import com.revature.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendUser {
	
	public SendUser(final User user) {
		this.id = user.getId();
		this.username = user.getUsername();
	}
	
	private int id;
	private String username;

}
