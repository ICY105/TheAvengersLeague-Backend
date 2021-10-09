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
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}
	
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;

}
