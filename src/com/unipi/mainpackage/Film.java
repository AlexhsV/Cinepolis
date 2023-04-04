package com.unipi.mainpackage;

public class Film {
	int filmID; // sum <- total films k to kathe film tha exei filmID= sum+1
	String filmTitle;
	String filmCategory;
	String filmDescription;
	boolean filmP18;
	int filmDuration;
	
	public Film( String filmTitle, String filmCategory, String filmDescription, boolean filmP18, int filmDuration) {
		
		setTitle(filmTitle);
		setCategory(filmCategory);
		setDescription(filmDescription);
		setP18(filmP18);
		setDuration(filmDuration);
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
	

    public String getTitle (String filmTitle) {
    	return this.filmTitle;
    }
    
    
    public String getCategory(String filmCategory) {
    	return this.filmCategory;
    }
    
    
    public String getDescription(String filmDescription) {
    	return this.filmDescription;
    }
    
    
    public boolean getP18(boolean filmP18) {
    	return this.filmP18;
    }
    
    
    public int getDuration(int filmDuration) {
    	return this.filmDuration ;
    }
    
    
    
    	
	
}
