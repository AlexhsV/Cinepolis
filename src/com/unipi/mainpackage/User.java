	package com.unipi.mainpackage;

public abstract class User {
	String name;
	String username;
	String password;
	
	public User(String name, String username, String password) {
		this.name = name;
		this.username = username;
		this.password = password;
	}
}
