package com.revature.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.revature.models.GameHandler;
import com.revature.models.GameState;
import com.revature.models.json_mapping.SendGame;
import com.revature.models.json_mapping.UserTurn;

@RestController
@SessionAttributes("visitor")
@RequestMapping("/game")
@CrossOrigin(origins = "*")
public class GameController {
	
	private final GameHandler handler = GameHandler.getInstance();

	@GetMapping("/play")
	public ResponseEntity<?> getGame(@ModelAttribute("visitor") final Visitor visitor) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body("Not logged in.");
		
		final GameState game = this.handler.updateUser(visitor.getUser());
		
		if(game == null)
			return ResponseEntity.ok("In queue.");
		else
			return ResponseEntity.ok(new SendGame(visitor.getUserId(),game));
	}

	@PostMapping("/play")
	public ResponseEntity<String> updateGame(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final UserTurn userTurn) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body("Not logged in.");
		
		if(this.handler.setUserMove(visitor.getUser(), userTurn))
			return ResponseEntity.ok("Set users next move.");
		else
			return ResponseEntity.ok("It is not your turn.");
	}

}
