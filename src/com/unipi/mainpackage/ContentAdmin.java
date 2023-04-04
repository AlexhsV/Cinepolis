package com.unipi.mainpackage;

import java.util.ArrayList;

public final class ContentAdmin extends User {

	public ContentAdmin(String name, String username, String password) {
		super(name,username,password,1);
		// TODO Auto-generated constructor stub
	}
	
	public void insertFilm(int FilmSum, String filmTitle,String filmCategory, String filmDescription , boolean filmP18, int filmDuration, ArrayList<Film> Films_Array ) {
		Films_Array.add(new Film(FilmSum,filmTitle, filmCategory, filmDescription , filmP18,  filmDuration));
		
	}
	
    public void deleteFilm(int filmID, ArrayList<Film> Films) {
    	
    	int temp = searchFilm(filmID, Films);
    	
    	if(temp>-1) {
    		Films.remove(temp);
    		for(int i = temp ; i <Films.size() ;i++ ) {
    			Films.get(i).setFilmID(Films.get(i).getFilmID()-1);
    		}
    	}
    	
    	System.out.println("film deleted");
	}
	
    public void assignFilmToCinema(int filmID) {
    	//me ton setter tou kathe cinema kanei assign ena film antikeimeno
    }  

    public int searchFilm(int filmID, ArrayList<Film> Films) {
    	
    	for(int i=0 ; i< Films.size() ; i++) {
			if(filmID == Films.get(i).getFilmID()){
				return i;
			}
		}
		
		return -1;
    }  

}
 