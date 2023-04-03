	package com.unipi.mainpackage;

import java.util.ArrayList;

public abstract class User {
	String name;
	String username;
	String password;
	int user_type; //0 admin 1 content admin 2customer int giati einai pio eukolo apo char h strings sthn xrhsh px an theloume na kanoume gia int = vazoume = enw gia string equals
	ArrayList<String> Users_Array = new ArrayList<String>(); //holds basic information about user -> username, password,type(admin,customer,contentadmin),name
	
	public User(String name, String username, String password, int user_type) {
		setName(name);
		setUsername(username);
		setPassword(password);
		setType(user_type);
	}
	
	public static boolean login(String username, String password) { //boolean gia na xeroume an egine to login/logout
		System.out.println("user logged in succesfully");
		//thn thelouem static giati tha einai koinh gia ola ta antikeimena giati apla pairnei apo textox usrname password kai ta psaxne isthn vash
		return true;
	}
	public static boolean logout() {
		System.out.println("user logged out succesfully");
		return true;
	}
	

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setType(int user_type) {
		this.user_type = user_type;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public int getType() {
		return this.user_type;
	}
	
	
    //etsi oste oloi oi user na mporoun na allaxoun onoma kwdiko kai na kanoun delete acc
	
	public void updateUser(String usernameForUpdate,String name, String username, String password) {
		//from textbox getting usernameForUpdate

	
		if (searchUser(usernameForUpdate)) {
		//from textboxes getting the new data
	

	//	if (typeOfuser.equals("Customer")){
			//updates the old data with the new
	//	}
	//	else if (typeOfuser.equals("ContentAdmin")) {
			//updates the old data with the new
	//	}

		}
		else {
			System.out.println("cannot proceed to update because the user does not exist");
		}
	}
	public void deleteUser(String usernameForDelete) {
		//from textbox getting the username for the user to delete
		
		if (searchUser(usernameForDelete)) {
			//delete the user
		}
		else {
		System.out.println("cannot proceed to delete because the user does not exist");
		}
	}

	public boolean searchUser(String usernameForSearch) {
	

		//search all users for the username provided
		//if (usernameForSearch exists in database
			return true;
		//else return false;	
	}

    public ArrayList<String> viewAllUsers() {
			
		
		return  new ArrayList<String>();
    }
	

	
	
	
	
}
