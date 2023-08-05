package basicpack;


import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{  //cinema = aithousa
	private String cinemaID;
	private boolean cinemasIs3D;
	private int cinmeaNumberOfSeats;
	private static ArrayList<Cinema> Cinemas_Array = new ArrayList<Cinema>();
	//array 2d theseis seires
	
	public Cinema(String cinemaID, boolean cinemasIs3D, int cinmeaNumberOfSeats) {
		this.cinemaID = cinemaID;
		this.cinemasIs3D = cinemasIs3D;
		this.cinmeaNumberOfSeats = cinmeaNumberOfSeats;
	}

	public String getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(String cinemaID) {
		this.cinemaID = cinemaID;
	}

	public boolean getCinemasIs3D() {
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

	public static ArrayList<Cinema> getCinemas_Array() {
		return Cinemas_Array;
	}

	public static void setCinemas_Array(ArrayList<Cinema> cinemas_Array) {
		Cinemas_Array = cinemas_Array;
	}
	

	
}
