package basicpack;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalTime;


public class Provoli implements Serializable {
	private String provoliID;
	private Film provoliFilm;
	private Cinema provoliCinema;
	private String provoliDay;
	private LocalTime provoliStartTime;
	private LocalTime provoliEndTime; 
	private int provoliNumberOfReservations;
	private boolean provoliIsAvailable;
	private static ArrayList<Provoli> Provoles_Array = new ArrayList<Provoli>();
	//array 2d theseis seires to opoio penrei apo to cinema tou 
    //na valoume ston customer dinatotia na dei an oi theseis pou dialexe yparxoun h oxi (provoliAvailable)
	
	public Provoli(Film provoliFilm,Cinema provoliCinema, String provoliDay, LocalTime provoliStartTime, boolean provoliIsAvailable) {
	
		this.provoliID = provoliFilm.getFilmTitle()+"_"+provoliCinema.getCinemaID()+"_"+provoliStartTime.toString();
		this.provoliFilm = provoliFilm;
		this.provoliCinema = provoliCinema;
		this.provoliDay = provoliDay;
		this.provoliStartTime = provoliStartTime;
		this.provoliEndTime = provoliStartTime.plus(provoliFilm.getFilmDuration());
		this.provoliNumberOfReservations = 0;
		this.provoliIsAvailable = provoliIsAvailable;
		// kanei get xoritikotita aithousas kai elegxedi an xwraei ara einai avilable
		//pinakas me oles tis provoles den diagrafontai apla  to available gienta itrue false
		//to provoiliISAVAILABKLE einai kalo na uaprxei sto database apla se auton pou kleinei tha deixnoume mono tis available provoles
	}

	public String getProvoliID() {
		return provoliID;
	}

	public void setProvoliID(String provoliID) {
		this.provoliID = provoliID;
	}

	public Film getProvoliFilm() {
		return provoliFilm;
	}

	public void setProvoliFilm(Film provoliFilm) {
		this.provoliFilm = provoliFilm;
	}

	public Cinema getProvoliCinema() {
		return provoliCinema;
	}

	public void setProvoliCinema(Cinema provoliCinema) {
		this.provoliCinema = provoliCinema;
	}

	public String getProvoliDay() {
		return provoliDay;
	}

	public void setProvoliDay(String provoliDay) {
		this.provoliDay = provoliDay;
	}

	public LocalTime getProvoliStartTime() {
		return provoliStartTime;
	}

	public void setProvoliStartTime(LocalTime provoliStartTime) {
		this.provoliStartTime = provoliStartTime;
		this.provoliEndTime = provoliStartTime.plus(provoliFilm.getFilmDuration());
	}

	public LocalTime getProvoliEndTime() {
		return provoliEndTime;
	}

	public void setProvoliEndTime(LocalTime provoliEndTime) {
		this.provoliEndTime = provoliEndTime;
	}

	public int getProvoliNumberOfReservations() {
		return provoliNumberOfReservations;
	}

	public void setProvoliNumberOfReservations(int provoliNumberOfReservations) {
		this.provoliNumberOfReservations = provoliNumberOfReservations;
	}

	public boolean getProvoliIsAvailable() {
		return provoliIsAvailable;
	}

	public void setProvoliIsAvailable(boolean provoliIsAvailable) {
		this.provoliIsAvailable = provoliIsAvailable;
	}

	public static ArrayList<Provoli> getProvoles_Array() {
		return Provoles_Array;
	}

	public static void setProvoles_Array(ArrayList<Provoli> provoles_Array) {
		Provoles_Array = provoles_Array;
	}

	@Override
	public String toString() {
		return "Provoli [provoliID=" + provoliID + ", provoliFilm=" + provoliFilm + ", provoliCinema=" + provoliCinema
				+ ", provoliDay=" + provoliDay + ", provoliStartTime=" + provoliStartTime + ", provoliEndTime="
				+ provoliEndTime + ", provoliNumberOfReservations=" + provoliNumberOfReservations
				+ ", provoliIsAvailable=" + provoliIsAvailable + "]";
	}

	public boolean checkProvoliIsAvailable() {
		if (provoliNumberOfReservations < provoliCinema.getCinmeaNumberOfSeats()) {
			provoliIsAvailable = true;
			return provoliIsAvailable;
		}
		else{
			provoliIsAvailable = false;
			return provoliIsAvailable;
		}
	}

	
	
}
