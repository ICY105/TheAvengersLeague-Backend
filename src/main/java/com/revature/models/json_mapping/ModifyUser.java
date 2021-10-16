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
	private String newPassword;
	private String currentPassword;

	public boolean vaildFirstName() {
		return this.firstName != null && this.firstName.length() >= 1;
	}

	public boolean vaildLastName() {
		return this.lastName != null && this.lastName.length() >= 1;
	}

	public boolean vaildPasswordName() {
		return this.newPassword != null && this.newPassword.length() >= 3;
	}

}
