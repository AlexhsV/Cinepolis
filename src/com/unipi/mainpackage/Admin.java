package com.unipi.mainpackage;

public final class Admin extends User{

	public Admin(String name, String username, String password) {
		super(name,username,password,0);
		// TODO Auto-generated constructor stub
	}

	public void createUser(String typeOfuser) {    //auto einai edw gt mono o admin kanei create user 
		//from textboxes
		String name = "alexhs v";
		String username = "SavageAlexhs";
		String password = "mlkas123";
		if (typeOfuser.equals("Customer")){
			String dateOfBirth = "24/09/2000";
			Customer c = new Customer(name, username, password, dateOfBirth);
		}
		else if (typeOfuser.equals("ContentAdmin")) {
			ContentAdmin ca = new ContentAdmin(name, username, password);
			ca.insertFilm("Oi Akataliloi","Action","empty description", false, 158);
		}
	}
}
