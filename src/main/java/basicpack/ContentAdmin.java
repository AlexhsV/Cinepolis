package basicpack;

import java.time.Duration;
import java.time.LocalTime;

public final class ContentAdmin extends User {

	public ContentAdmin(String name, String username, String password) {
		super(name,username,password,1);
		// TODO Auto-generated constructor stub
	}
	
	public void insertFilm(String filmTitle,String filmCategory, String filmDescription , boolean filmP18, Duration filmDuration, String filmDateOfPremiere) {
		Film.getFilms_Array().add(new Film(filmTitle, filmCategory, filmDescription , filmP18, filmDuration, filmDateOfPremiere));
	}
	
    public void deleteFilm(int filmID) {
    	
    	int temp = searchFilm(filmID);
    	
    	if(temp>-1) {
    		Film.getFilms_Array().remove(temp);
    		for(int i = temp ; i <Film.getFilms_Array().size() ;i++ ) {
    			Film.getFilms_Array().get(i).setFilmID(Film.getFilms_Array().get(i).getFilmID()-1);
    		}
    	}
    	
    	System.out.println("film deleted");
	}
	
    public int searchFilm(int filmID) {
    	
    	for(int i=0 ; i< Film.getFilms_Array().size() ; i++) {
			if(filmID == Film.getFilms_Array().get(i).getFilmID()){
				return i;
			}
		}
		
		return -1;
    }  
    
    public void createProvoli( Film provoliFilm, Cinema provoliCinema, String provoliDay, LocalTime provoliStartTime, boolean filmIsAvailable) {
		Provoli.getProvoles_Array().add( new Provoli( provoliFilm, provoliCinema, provoliDay , provoliStartTime, filmIsAvailable));
		
	}

}
 