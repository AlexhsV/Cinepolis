package com.unipi.mainpackage;

public final class Customer extends User {
	String dateOfBirth; // in order to access an adult film

	public Customer(String name, String username, String password, String dateOfBirth) {
		super(name,username,password);
		this.dateOfBirth = dateOfBirth;
	}
		

}
