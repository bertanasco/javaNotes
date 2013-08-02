package com.jpanotes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	private Long Id;
	private String password;
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//Why does Hibernate require no argument constructor?
	//http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor/2971717#2971717
	public User(){

	}

}
