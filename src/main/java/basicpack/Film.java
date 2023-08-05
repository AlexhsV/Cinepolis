package basicpack;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.Duration;

public class Film implements Serializable{
	private int filmID; // sum <- total films k to kathe film tha exei filmID= sum+1
	private String filmTitle;
	private String filmCategory;
	private String filmDescription;
	private boolean filmP18;
	private Duration filmDuration;
	private String filmDateOfPremiere;
	private static ArrayList<Film> Films_Array = new ArrayList<Film>();
	
	
	public Film(String filmTitle, String filmCategory, String filmDescription, boolean filmP18, Duration filmDuration, String filmDateOfPremiere) {

	    this.filmID ++;
		this.filmTitle = filmTitle;
		this.filmCategory = filmCategory;
		this.filmDescription = filmDescription;
		this.filmP18 = filmP18;
		this.filmDuration = filmDuration;
		this.filmDateOfPremiere = filmDateOfPremiere;
	}


	public int getFilmID() {
		return filmID;
	}


	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}


	public String getFilmTitle() {
		return filmTitle;
	}


	public void setFilmTitle(String filmTitle) {
		this.filmTitle = filmTitle;
	}


	public String getFilmCategory() {
		return filmCategory;
	}


	public void setFilmCategory(String filmCategory) {
		this.filmCategory = filmCategory;
	}


	public String getFilmDescription() {
		return filmDescription;
	}


	public void setFilmDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}


	public boolean isFilmP18() {
		return filmP18;
	}


	public void setFilmP18(boolean filmP18) {
		this.filmP18 = filmP18;
	}


	public Duration getFilmDuration() {
		return filmDuration;
	}


	public void setFilmDuration(Duration filmDuration) {
		this.filmDuration = filmDuration;
		for (Provoli provoli : Provoli.getProvoles_Array()) {
			  if (provoli.getProvoliFilm().filmID == this.filmID) {
				  provoli.getProvoliFilm().setFilmDuration(filmDuration);;
			  }
			}
	}

	public String getFilmDateOfPremiere() {
		return filmDateOfPremiere;
	}


	public void setFilmDateOfPremiere(String filmDateOfPremiere) {
		this.filmDateOfPremiere = filmDateOfPremiere;
	}

	public static ArrayList<Film> getFilms_Array() {
		return Films_Array;
	}


	public static void setFilms_Array(ArrayList<Film> films_Array) {
		Films_Array = films_Array;
	}


	
}
