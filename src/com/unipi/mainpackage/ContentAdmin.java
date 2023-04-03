package com.unipi.mainpackage;

public final class ContentAdmin extends User {

	public ContentAdmin(String name, String username, String password) {
		super(name,username,password,1);
		// TODO Auto-generated constructor stub
	}
	
	public void insertFilm(String filmTitle,String filmCategory, String filmDescription , boolean filmP18, int filmDuration ) {
		new Film(filmTitle, filmCategory, filmDescription , filmP18,  filmDuration);
	}
	
    public void deleteFilm(int filmID) {
    	System.out.println("film deleted");
	}
	
    public void assignFilmToCinema(int filmID) {
    	//me ton setter tou kathe cinema kanei assign ena film antikeimeno
    }  


}
 