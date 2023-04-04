package com.unipi.mainpackage;

public class Cinema {
	int cinemaID;
	boolean cinemasIs3D;
	int cinmeaNumberOfSeats;
	
	public Cinema(int cinemaID, boolean cinemasIs3D, int cinmeaNumberOfSeats) {
		this.cinemaID = cinemaID;
		this.cinemasIs3D = cinemasIs3D;
		this.cinmeaNumberOfSeats = cinmeaNumberOfSeats;
	}

	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}

	public boolean isCinemasIs3D() {
		return cinemasIs3D;
	}

	public void setCinemasIs3D(boolean cinemasIs3D) {
		this.cinemasIs3D = cinemasIs3D;
	}

	public int getCinmeaNumberOfSeats() {
		return cinmeaNumberOfSeats;
	}

	public void setCinmeaNumberOfSeats(int cinmeaNumberOfSeats) {
		this.cinmeaNumberOfSeats = cinmeaNumberOfSeats;
	}
	
	
}
