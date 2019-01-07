package com.example.demo.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "NAME")
	@Size(min = 2,max=20, message= "name characters size must bewteen 2 and 20 length")
	private String name;
	
	@Column(name = "ROLE")
	private String role;

	
	public User() {
		super();
	}

	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
