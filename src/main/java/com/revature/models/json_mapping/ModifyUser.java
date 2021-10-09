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
	
	public String validate() {
		if(this.firstName != null && this.firstName.length() < 1)
			return "First name cannot be blank.";
		if(this.lastName != null && this.lastName.length() < 1)
			return "Last name cannot be blank.";
		if(this.password != null && this.password.length() < 3)
			return "Password much be at least 3 characters long.";
		if(this.email == null || this.email.indexOf('@') > 1)
			return "Not a valid email.";
		return "valid";
	}

}
