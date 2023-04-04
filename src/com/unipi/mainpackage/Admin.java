package com.unipi.mainpackage;

import java.util.ArrayList;

public final class Admin extends User{

	public Admin(String name, String username, String password) {
		super(name,username,password,0);
		// TODO Auto-generated constructor stub
	}

	public Customer createCustomer(String name ,String username, String password, String dateOfBirth) {    
		
		
		
			
			return new Customer(name, username, password, dateOfBirth);
		    
		
		
		
	}
	
	public ContentAdmin createContentAdmin(String name ,String username, String password) {    
		
	
			return new ContentAdmin(name, username, password);

			
		    	
			
		
		
		
		
	}
	
	public Admin createAdmin(String name ,String username, String password) {   
		
		
			
		    return new Admin(name,username,password);
			
		
		
		
		
	}
	
	// sthn arxh tou programmatos kanoume Admin Session -> o customer thn xrisimopoiei gia na allazei onoma+ del acc
	
	public void updateUser(String usernameForUpdate,String name, String username, String password, ArrayList<User> User_Array) {
		//from textbox getting usernameForUpdate

	    int temp = searchUser(usernameForUpdate,User_Array);
		if (temp>=0) {
		
			User_Array.get(temp).setName(name);
			User_Array.get(temp).setUsername(username);
			User_Array.get(temp).setPassword(password);
			
		}
		else {
			System.out.println("cannot proceed to update because the user does not exist");
		}
	}
	
	public void deleteUser(String usernameForDelete,ArrayList<User> Users_Array) {
		//from textbox getting the username for the user to delete
		int temp = searchUser(usernameForDelete,Users_Array);
		if (temp>=0) {
			Users_Array.remove(temp);
		}
		else {
		System.out.println("cannot proceed to delete because the user does not exist");
		}
	}

	
	public int searchUser(String usernameForSearch, ArrayList<User> Users_Array) {  //einai tou admin -> mexri na vroume lush gia to panw searchUser
		

		for(int i=0 ; i< Users_Array.size() ; i++) {
			if(usernameForSearch.equals(Users_Array.get(i).getUsername())) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	public void viewAllUsers( ArrayList<User> Users_Array) {
		 for(int i = 0 ; i<Users_Array.size();i++) {
	        	System.out.println(Users_Array.get(i).getName());
	        	System.out.println(Users_Array.get(i).getUsername());
	        	System.out.println(Users_Array.get(i).getPassword());
	        	System.out.println(Users_Array.get(i).getType());
	        	if(Users_Array.get(i).getType()==2) {
	        		System.out.println(((Customer) Users_Array.get(i)).getDateOfBirth());
	        	}
	        }
	}

	
	
	

}
