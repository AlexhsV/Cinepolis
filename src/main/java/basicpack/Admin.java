package basicpack;


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
	
	public void updateUser(String usernameForUpdate,String name, String username, String password) {
		//from textbox getting usernameForUpdate

	    int temp = searchUser(usernameForUpdate);
		if (temp>=0) {
		
			User.getUsers_Array().get(temp).setName(name);
			getUsers_Array().get(temp).setUsername(username);
			getUsers_Array().get(temp).setPassword(password);
			
		}
		else {
			System.out.println("cannot proceed to update because the user does not exist");
		}
	}
	
	public void deleteUser(String usernameForDelete) {
		//from textbox getting the username for the user to delete
		int temp = searchUser(usernameForDelete);
		if (temp>=0) {
			getUsers_Array().remove(temp);
		}
		else {
		System.out.println("cannot proceed to delete because the user does not exist");
		}
	}

	
	public int searchUser(String usernameForSearch) {  //einai tou admin -> mexri na vroume lush gia to panw searchUser
		

		for(int i=0 ; i< getUsers_Array().size() ; i++) {
			if(usernameForSearch.equals(getUsers_Array().get(i).getUsername())) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	public void viewAllUsers() {
		 for(int i = 0 ; i<getUsers_Array().size();i++) {
	        	System.out.println(getUsers_Array().get(i).getName());
	        	System.out.println(getUsers_Array().get(i).getUsername());
	        	System.out.println(getUsers_Array().get(i).getPassword());
	        	System.out.println(getUsers_Array().get(i).getUser_type());
	        	if(getUsers_Array().get(i).getUser_type()==2) {
	        		System.out.println(((Customer) getUsers_Array().get(i)).getDateOfBirth());
	        	}
	        }
	}

	
	public void createCinema(String cinemaID, boolean cinemasIs3D, int cinmeaNumberOfSeats) {
		//Cinema.getCinemas_Array().add( new Cinema(cinemaID, cinemasIs3D, cinmeaNumberOfSeats));
		
	}
	

}
