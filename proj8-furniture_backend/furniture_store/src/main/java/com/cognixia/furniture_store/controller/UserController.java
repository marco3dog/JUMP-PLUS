package com.cognixia.furniture_store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.furniture_store.exception.ResourceNotFoundException;
import com.cognixia.furniture_store.model.User;
import com.cognixia.furniture_store.service.UserService;




@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;

	@Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/user")
    public List<User> getUsers() {
		
		return service.getUsers();
	}
    

    @GetMapping("/user/{id}")
	public ResponseEntity<?> getUser(@PathVariable int id) throws ResourceNotFoundException {
		
		User found = service.getUserById(id);
		
		return ResponseEntity.status(200).body( found );
		 
	}
	@GetMapping("/user/name/{username}")
	public ResponseEntity<?>getUserByUsername(@PathVariable String username) throws ResourceNotFoundException{
		User found=service.getUserByUsername(username);
		return ResponseEntity.status(200).body(found);
	
	}
    @PostMapping("/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
		user.setId(null);
		user.setPassword(encoder.encode(user.getPassword()));
		User created = service.createUser(user);
		
		return ResponseEntity.status(201).body(created);
	}

    @PutMapping("/user")
	public ResponseEntity<?> updateUser(@Valid @RequestBody User user) throws ResourceNotFoundException {
		
		User updated = service.updateUser(user);
		
		return ResponseEntity.status(200)
							 .body(updated);
		
	}

    @DeleteMapping("/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
		
		service.deleteUser(id);
			
		return ResponseEntity.status(200)
							 .body("Deleted user with id = " + id);
	
	}
	
    
}
