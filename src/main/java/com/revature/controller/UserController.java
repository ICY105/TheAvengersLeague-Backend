package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.revature.models.User;
import com.revature.models.json_mapping.CreateUser;
import com.revature.models.json_mapping.JsonError;
import com.revature.models.json_mapping.JsonStatus;
import com.revature.models.json_mapping.LoginUser;
import com.revature.models.json_mapping.ModifyUser;
import com.revature.models.json_mapping.ModifyUserDecks;
import com.revature.models.json_mapping.SendUser;
import com.revature.models.json_mapping.SendUserComplete;
import com.revature.models.json_mapping.SendUserDecks;
import com.revature.models.json_mapping.SendUserInventory;
import com.revature.service.UserService;

@RestController
@SessionAttributes("visitor")
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/id={id}")
	public ResponseEntity<?> findById(@ModelAttribute("visitor") final Visitor visitor, @PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body(new JsonError("No user exists with id " + id + "."));
		
		if(visitor.getUserId() == user.getId())
			return ResponseEntity.ok(new SendUserComplete(user));
		else
			return ResponseEntity.ok(new SendUser(user));
	}
	
	@GetMapping("/username={username}")
	public ResponseEntity<?> findByUsername(@ModelAttribute("visitor") final Visitor visitor, @PathVariable("username") final String username) {
		final User user = this.userService.findByUsername(username);
		if(user == null)
			return ResponseEntity.badRequest().body(new JsonError("No user exists with username " + username + "."));
		
		if(visitor.getUserId() == user.getId())
			return ResponseEntity.ok(new SendUserComplete(user));
		else
			return ResponseEntity.ok(new SendUser(user));
	}
	
	@GetMapping("/decks/id={id}")
	public ResponseEntity<?> getDecks(@ModelAttribute("visitor") final Visitor visitor, @PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body(new JsonError("No user exists with id " + id + "."));
		else
			return ResponseEntity.ok(new SendUserDecks(user));
	}
	
	@GetMapping("/inventory/id={id}")
	public ResponseEntity<?> getInventory(@ModelAttribute("visitor") final Visitor visitor, @PathVariable("id") final int id) {
		final User user = this.userService.findById(id);
		if(user == null)
			return ResponseEntity.badRequest().body(new JsonError("No user exists with id " + id + "."));
		else
			return ResponseEntity.ok(new SendUserInventory(user));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(@ModelAttribute("visitor") final Visitor visitor) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body(new JsonError("Not logged in."));
		
		visitor.setUserId(-1);
		return ResponseEntity.ok(new JsonStatus("Logged out."));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final LoginUser loginUser) {
		if(visitor.getUserId() != -1)
			return ResponseEntity.badRequest().body(new JsonError("Logout before trying to log in as a new user."));
		
		if(loginUser.getId() != 0) {
			final User user = this.userService.findById(loginUser.getId());
			
			if(user == null)
				return ResponseEntity.badRequest().body(new JsonError("No user exists with id " + loginUser.getId() + "."));
			if(!user.getPassword().equals(loginUser.getPassword()))
				return ResponseEntity.badRequest().body(new JsonError("Incorrect password for user " + loginUser.getId() + "."));
			
			visitor.setUserId(user.getId());
			return ResponseEntity.ok(new SendUserComplete(user));
		} else if(loginUser.getUsername() != null) {
			final User user = this.userService.findByUsername(loginUser.getPassword());
			
			if(user == null)
				return ResponseEntity.badRequest().body(new JsonError("No user exists with id " + loginUser.getUsername() + "."));
			if(!user.getPassword().equals(loginUser.getPassword()))
				return ResponseEntity.badRequest().body(new JsonError("Incorrect password for user " + loginUser.getUsername() + "."));
			
			visitor.setUserId(user.getId());
			return ResponseEntity.ok(new SendUserComplete(user));
		}
		return ResponseEntity.badRequest().body(new JsonError("Invalid login json object."));
	}
	
	@PostMapping("/modify")
	public ResponseEntity<?> modifyUser(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final ModifyUser modifyUser) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body(new JsonError("Not logged in as a user."));
		
		final User user = this.userService.findById(visitor.getUserId());
		if(modifyUser.vaildFirstName())
			user.setFirstName(modifyUser.getFirstName());
		if(modifyUser.vaildLastName())
			user.setLastName(modifyUser.getLastName());
		if(modifyUser.vaildEmailName())
			user.setEmail(modifyUser.getEmail());
		if(modifyUser.vaildPasswordName())
			user.setPassword(modifyUser.getPassword());
		
		return ResponseEntity.ok(new SendUserComplete(user));
	}
	
	@PostMapping("/modify/decks")
	public ResponseEntity<?> modifyUserDecks(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final ModifyUserDecks modifyDecks) {
		if(visitor.getUserId() == -1)
			return ResponseEntity.badRequest().body(new JsonError("Not logged in as a user."));
		
		final User user = this.userService.findById(visitor.getUserId());
		if(modifyDecks.validHeroDeck())
			user.setHeroDeck(modifyDecks.getHeroDeck());
		if(modifyDecks.validVillianDeck())
			user.setVillianDeck(modifyDecks.getVillianDeck());
		
		if(modifyDecks.validHeroDeck() || modifyDecks.validVillianDeck())
			return ResponseEntity.ok(new SendUserComplete(user));
		else
			return ResponseEntity.badRequest().body(new JsonError("No valid decks recieved."));
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> createUser(@ModelAttribute("visitor") final Visitor visitor, @RequestBody final CreateUser createUser) {
		final String validate = createUser.validate();
		
		if(validate.equals("valid")) {
			final User user = createUser.getUserObject();
			
			if(this.userService.findByUsername(user.getUsername()) != null)
				return ResponseEntity.ok(new JsonError("Username is already taken."));
			if(this.userService.findByEmail(user.getEmail()) != null)
				return ResponseEntity.ok(new JsonError("Email is already taken."));
			
			final User dbUser = this.userService.insert(user);
			visitor.setUserId(dbUser.getId());
			visitor.setUser(dbUser);
			return ResponseEntity.ok(new SendUserComplete(dbUser));
			
		} else {
			return ResponseEntity.badRequest().body(validate);
		}
	}
	
	@ModelAttribute("visitor")
    public Visitor getVisitor () {
        return new Visitor();
    }
	
}
