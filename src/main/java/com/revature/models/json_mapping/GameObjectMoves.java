package com.revature.models.json_mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameObjectMoves {
	
	String uuid;
	int id;
	int x;
	int y;
	
	public boolean hasUuid() {
		return this.uuid != null && this.uuid.length() >= 32 && this.uuid.length() <= 36;
	}

}
