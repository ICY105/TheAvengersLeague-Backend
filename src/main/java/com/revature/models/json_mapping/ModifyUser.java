package com.revature.models.json_mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUser {
	
	private String firstName;
	private String lastName;
	private String password;
	private String email;

	public boolean vaildFirstName() {
		return this.firstName != null && this.firstName.length() >= 1;
	}

	public boolean vaildLastName() {
		return this.lastName != null && this.lastName.length() >= 1;
	}

	public boolean vaildEmailName() {
		return this.email != null && this.email.length() >= 3;
	}

	public boolean vaildPasswordName() {
		return this.password != null && this.email.indexOf('@') > 1;
	}

}
