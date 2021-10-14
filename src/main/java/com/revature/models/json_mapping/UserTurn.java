package com.revature.models.json_mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTurn {
	
	int power;
	int[] hand;
	GameObjectMoves[] moves;
	
}
