package com.unipi.mainpackage;

public class Film {
	private int filmID; // sum <- total films k to kathe film tha exei filmID= sum+1
	private String filmTitle;
	private String filmCategory;
	private String filmDescription;
	private boolean filmP18;
	private int filmDuration;
	
	
	
	public Film(int FilmSum, String filmTitle, String filmCategory, String filmDescription, boolean filmP18,	int filmDuration) {
		FilmSum++;
	    this.filmID = FilmSum;
		this.filmTitle = filmTitle;
		this.filmCategory = filmCategory;
		this.filmDescription = filmDescription;
		this.filmP18 = filmP18;
		this.filmDuration = filmDuration;
	}



	public void setTitle (String filmTitle) {
		this.filmTitle = filmTitle;
	}
	

	public void setCategory(String filmCategory) {
		this.filmCategory = filmCategory;
	}
	

	public void setDescription(String filmDescription) {
		this.filmDescription = filmDescription;
	}
	

	public void setP18(boolean filmP18) {
		this.filmP18 = filmP18;
	}
	

	public void setDuration(int filmDuration) {
		this.filmDuration = filmDuration;
	}
	
	public void setFilmID(int filmID) {
		this.filmID = filmID;
	}
	
	

    public String getTitle () {
    	return filmTitle;
    }
    
    
    public String getCategory() {
    	return filmCategory;
    }
    
    
    public String getDescription() {
    	return filmDescription;
    }
    
    
    public boolean getP18(boolean filmP18) {
    	return filmP18;
    }
    
    
    public int getDuration() {
    	return filmDuration ;
    }
    
    public int getFilmID() {
    	return this.filmID ;
    }
    
    
    	
	
}
