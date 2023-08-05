package basicpack;


public final class Customer extends User {
	
	String dateOfBirth; // in order to access an adult film

	public Customer(String name, String username, String password, String dateOfBirth) {
		super(name,username,password,2);
		this.dateOfBirth = dateOfBirth;
	}
		

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String showAvailableFilms() {
		String availableFilms = "films123...";
		return availableFilms;
	}
	public void makeReservation(String provoliID, int numberOfSeats) {
		System.out.println("choose date, time");
		for (Provoli provoli : Provoli.getProvoles_Array()) {
    	    if(provoli.getProvoliID() == provoliID && provoli.getProvoliIsAvailable()) {
    	    	provoli.setProvoliNumberOfReservations(provoli.getProvoliNumberOfReservations() + 1);
    	    }
    	}
		System.out.println(numberOfSeats + " seats are booked for the film" + provoliID);
	}
	public String showReservation(int provoliID) {
		String reservation = "name,date,time...";
		return reservation;
	}
}
