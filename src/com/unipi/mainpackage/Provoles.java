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

	public int getProvoliID() {
		return provoliID;
	}

	public void setProvoliID(int provoliID) {
		this.provoliID = provoliID;
	}

	public String getProvoliFilm() {
		return provoliFilm;
	}

	public void setProvoliFilm(String provoliFilm) {
		this.provoliFilm = provoliFilm;
	}

	public String getProvoliStartDate() {
		return provoliStartDate;
	}

	public void setProvoliStartDate(String provoliStartDate) {
		this.provoliStartDate = provoliStartDate;
	}

	public String getProvoliEndDate() {
		return provoliEndDate;
	}

	public void setProvoliEndDate(String provoliEndDate) {
		this.provoliEndDate = provoliEndDate;
	}

	public int getProvoliNumberOfReservations() {
		return provoliNumberOfReservations;
	}

	public void setProvoliNumberOfReservations(int provoliNumberOfReservations) {
		this.provoliNumberOfReservations = provoliNumberOfReservations;
	}

	public String getProvolesAvailable() {
		return provolesAvailable;
	}

	public void setProvolesAvailable(String provolesAvailable) {
		this.provolesAvailable = provolesAvailable;
	}
	
	
	
}
