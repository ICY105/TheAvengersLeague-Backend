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
import com.revature.models.json_mapping.JsonError;
import com.revature.models.json_mapping.JsonStatus;
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
			return ResponseEntity.badRequest().body(new JsonError("Not logged in."));
		
		final GameState game = this.handler.updateUser(visitor.getUser());
		
		if(game == null) {
			return ResponseEntity.ok(new JsonStatus("In queue."));
		} else {
			if(game.needUpdate(visitor.getUserId()))
				return ResponseEntity.ok(new SendGame(visitor.getUserId(),game));
			else
				return ResponseEntity.ok(new JsonStatus("no update"));
		}
	}

	@GetMapping("/play-force")
	public ResponseEntity<?> getGameForce(@ModelAttribute("visitor") final Visitor visitor) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body(new JsonError("Not logged in."));
		
		final GameState game = this.handler.updateUser(visitor.getUser());
		if(game == null) {
			return ResponseEntity.ok(new JsonStatus("In queue."));
		} else {
			return ResponseEntity.ok(new SendGame(visitor.getUserId(),game));
		}
	}

	@PostMapping("/play")
	public ResponseEntity<?> updateGame(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final UserTurn userTurn) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body(new JsonError("Not logged in."));
		
		final String response = this.handler.setUserMove(visitor.getUser(), userTurn);
		if(response.equals("valid"))
			return ResponseEntity.ok(new JsonStatus("Set users next move."));
		else
			return ResponseEntity.ok(new JsonError(response));
	}

}
