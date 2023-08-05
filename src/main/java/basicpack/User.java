	package basicpack;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {
	private String name;
	private String username;
	private String password;
	private int user_type; //0 admin 1 content admin 2customer int giati einai pio eukolo apo char h strings sthn xrhsh px an theloume na kanoume gia int = vazoume = enw gia string equals
	private static ArrayList<User> Users_Array = new ArrayList<User>(); //holds basic information about user -> username, password,type(admin,customer,contentadmin),name
	

	
	public User(String name, String username, String password, int user_type) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
	
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
	
	
	public String getName() {
		return name;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	public static ArrayList<User> getUsers_Array() {
		return Users_Array;
	}

	public static void setUsers_Array(ArrayList<User> users_Array) {
		Users_Array = users_Array;
	}

	
    //etsi oste oloi oi user na mporoun na allaxoun onoma kwdiko kai na kanoun delete acc
	
	
	

	
	
	
	
}
