package com.unipi.mainpackage;

public class Provoles {
	int provoliID;
	String provoliFilm;
	String provoliDateOf;
	String provoliStart;
	String provoliEnd;
	String provoliCinemaID;
	//array 2d theseis seires to opoio penrei apo to cinema tou 
    //na valoume ston customer dinatotia na dei an oi theseis pou dialexe yparxoun h oxi (provoliAvailable)
	
	public Provoles(int provoliID, String provoliFilm, String provoliDateOf, String provoliStart, String provoliEnd,
			String provoliCinemaID) {
	
		this.provoliID = provoliID;
		this.provoliFilm = provoliFilm;
		this.provoliDateOf = provoliDateOf;
		this.provoliStart = provoliStart;
		this.provoliEnd = provoliEnd;
		this.provoliCinemaID = provoliCinemaID;
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

	public String getProvoliDateOf() {
		return provoliDateOf;
	}

	public void setProvoliDateOf(String provoliDateOf) {
		this.provoliDateOf = provoliDateOf;
	}

	public String getProvoliStart() {
		return provoliStart;
	}

	public void setProvoliStart(String provoliStart) {
		this.provoliStart = provoliStart;
	}

	public String getProvoliEnd() {
		return provoliEnd;
	}

	public void setProvoliEnd(String provoliEnd) {
		this.provoliEnd = provoliEnd;
	}

	public String getProvoliCinemaID() {
		return provoliCinemaID;
	}

	public void setProvoliCinemaID(String provoliCinemaID) {
		this.provoliCinemaID = provoliCinemaID;
	}
	
	

	
  
	
	
}
