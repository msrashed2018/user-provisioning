package com.example.demo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
	public Resource<User>  getUserById(@PathVariable long id) {
		User user =  service.getUserById(id);
		if (user == null)
			throw new UserNotFoundException("id-" + id); 
		
		Resource<User> resource = new Resource<User>(user);
		addLinkToAllUsers(resource);
		return resource;
	}
	
	

	@GetMapping(path = "/users")
	public List<User> getUsers() {
		return service.getUsers();
	}
	
	@PostMapping(path = "/users")
	public Resource<User> createUser(@Valid @RequestBody User user) {
		User createdUser = service.createUser(user);
		
		Resource<User> resource = new Resource<User>(createdUser);
		addLinkToUser(resource, createdUser.getId());
		addLinkToAllUsers(resource);
		return resource;
	}
	
	@DeleteMapping(path = "/users/{id}")
	public Resource<User> deleteUserById(@PathVariable long id) {
		User user = service.deleteUserById(id);
		if(user == null)
			throw new UserNotFoundException("id-" + id);
		
		Resource<User> resource = new Resource<User>(user);
		addLinkToAllUsers(resource);
		return resource;
	}
	
	private void addLinkToAllUsers(Resource<User> resource) {
		ControllerLinkBuilder linkToAllUsers = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUsers());
		resource.add(linkToAllUsers.withRel("all-users"));		
	}
	
	private void addLinkToUser(Resource<User> resource,long id) {
		ControllerLinkBuilder linkToAllUsers = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).getUserById(id));
		resource.add(linkToAllUsers.withRel("user-id-"+id));		
	}
	 
	
}
