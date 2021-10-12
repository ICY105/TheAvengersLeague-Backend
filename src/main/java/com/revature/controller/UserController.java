package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.User;
import com.revature.models.json_mapping.CreateUser;
import com.revature.models.json_mapping.SendUser;
import com.revature.models.json_mapping.SendUserDecks;
import com.revature.models.json_mapping.SendUserInventory;
import com.revature.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/id={id}")
	public ResponseEntity<?> findById(@PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body("No user exists with id " + id + ".");
		else
			return ResponseEntity.ok(new SendUser(user));
	}
	
	@GetMapping("/username={username}")
	public ResponseEntity<?> findByUsername(@PathVariable("username") final String username) {
		final User user = this.userService.findByUsername(username);
		if(user == null)
			return ResponseEntity.badRequest().body("No user exists with username " + username + ".");
		else
			return ResponseEntity.ok(new SendUser(user));
	}
	
	@GetMapping("/decks/id={id}")
	public ResponseEntity<?> getDecks(@PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body("No user exists with id " + id + ".");
		else
			return ResponseEntity.ok(new SendUserDecks(user));
	}
	
	@GetMapping("/inventory/id={id}")
	public ResponseEntity<?> getInventory(@PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body("No user exists with id " + id + ".");
		else
			return ResponseEntity.ok(new SendUserInventory(user));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createUser(@RequestBody final CreateUser createUser) {
		final String validate = createUser.validate();
		
		if(validate.equals("valid")) {
			final User user = createUser.getUserObject();
			
			if(this.userService.findByUsername(user.getUsername()) != null)
				return ResponseEntity.ok("Username must be unique.");
			if(this.userService.findByEmail(user.getEmail()) != null)
				return ResponseEntity.ok("Email must be unique.");
			
			final User dbUser = this.userService.insert(user);
			return ResponseEntity.ok(new SendUser(dbUser));
			
		} else {
			return ResponseEntity.badRequest().body(validate);
		}
	}
	
}
