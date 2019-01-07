package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.beans.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository repository;

	public List<User> getUsers() {
		return repository.findAll();
	}
	public User createUser(User user) {
		 return repository.save(user);
	}

	public User deleteUserById(long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			repository.deleteById(id);
			return user.get();
		}else
			return null;
	}
	public User getUserById(long id) {
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}else
			return null; 
	}
}
