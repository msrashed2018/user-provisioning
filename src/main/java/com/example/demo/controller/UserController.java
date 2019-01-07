package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.beans.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	
	
	@GetMapping(path = "/user/{id}")
	public User  getUserById(@PathVariable long id) {
		User user =  service.getUserById(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id); 
		return user;
	}
	
	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return service.getUsers();
	}
	
	@PostMapping(path = "/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User createdUser = service.createUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/users/{id}")
	public User deleteUserById(@PathVariable long id) {
		User user = service.deleteUserById(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);
		return user;
	}
	 
	
}
