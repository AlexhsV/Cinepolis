package com.unipi.mainpackage;

public class Provoles {
	int provoliID;
	String provoliFilm;
	String provoliStartDate;
	String provoliEndDate;
	int provoliNumberOfReservations;
	String provolesAvailable; // what is this used for?
	
	public Provoles(int provoliID, String provoliFilm, String provoliStartDate, String provoliEndDate,
			int provoliNumberOfReservations, String provolesAvailable) {

		this.provoliID = provoliID;
		this.provoliFilm = provoliFilm;
		this.provoliStartDate = provoliStartDate;
		this.provoliEndDate = provoliEndDate;
		this.provoliNumberOfReservations = provoliNumberOfReservations;
		this.provolesAvailable = provolesAvailable;
	}
	
	
}
