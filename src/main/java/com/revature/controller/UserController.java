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
import com.revature.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable("id") final int id) {
		System.out.println("Get id: " + id);
		final User user = this.userService.findById(id);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<User> findByUsername(@PathVariable("username") final String username) {
		System.out.println("Get username: " + username);
		final User user = this.userService.findByUsername(username);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/add")
	public ResponseEntity<User> insert(@RequestBody final User user) {
		return ResponseEntity.ok(this.userService.insert(user));
	}
	
}
