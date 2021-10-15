package com.revature.models.json_mapping;

import com.revature.models.CommanderGameCard;
import com.revature.models.GameCard;
import com.revature.models.GameObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendGameObject {
	
	public SendGameObject(final GameObject obj) {
		this.x = obj.getX();
		this.y = obj.getY();
		this.uuid = obj.getUuid();
		this.affiliation = obj.getAffiliation().toString().toLowerCase();
		
		this.type = "GameObject";
		
		if(obj instanceof GameCard) {
			this.card = new SendCard( ((GameCard) obj).getCard() );
			this.health = ((GameCard) obj).getHealth();
			this.type = "GameCard";
			
			if(obj instanceof CommanderGameCard)
				this.type = "CommanderGameCard";
		}
	}
	
	private int x;
	private int y;
	private String uuid;
	private String affiliation;
	private String type;
	
	private SendCard card;
	private int health;

}
