package basicpack;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.sql.Connection;

public class Main{

	
    public static void main(String[] args) {
	 // new GUI(); to start gui -- later
    	
    	//on first run ever
    	
    	
    	try {
    		System.out.println("in");
    		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cinema_last","root","toor");

    		if(conn!=null)
    		{
    			System.out.println("success");
    		}
    		conn.close();
    		}
    	catch (Exception e){
    		System.out.println("nope");
    	}
    
    	
    	//deserialize
    	 try {
             FileInputStream fileIn = new FileInputStream("cinepolis.txt");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             System.out.println("USERS-----");
             @SuppressWarnings("unchecked")
             ArrayList<User> temp1 = (ArrayList<User>) in.readObject();
             for(@SuppressWarnings("unused") User user:temp1) {
            	 
            	 User.getUsers_Array().add(user);
            	System.out.println( user.getName());
             }
             System.out.println("FILMS-----");
             @SuppressWarnings("unchecked")
 			ArrayList<Film> temp2 = (ArrayList<Film>) in.readObject();
              for(@SuppressWarnings("unused") Film film:temp2) {
             
             	 Film.getFilms_Array().add(film);
             	System.out.println( film.getFilmTitle());
              }
              System.out.println("CINEMA-----");
              @SuppressWarnings("unchecked")
   			ArrayList<Cinema> temp3 = (ArrayList<Cinema>) in.readObject();
                for(@SuppressWarnings("unused") Cinema cinema:temp3) {
               
               	Cinema.getCinemas_Array().add(cinema);
               	System.out.println( cinema.getCinemaID());
                }
                System.out.println("PROVOLES-----");
             @SuppressWarnings("unchecked")
       	    ArrayList<Provoli> temp4 = (ArrayList<Provoli>) in.readObject();
                    for(@SuppressWarnings("unused") Provoli provoli:temp4) {
                  
                  Provoli.getProvoles_Array().add(provoli);
                 
                   	System.out.println( provoli.getProvoliID());
                    }
                   
                
                
             //ligma
             System.out.println("");
              
             in.close();
             fileIn.close();
             temp1=null;
             temp2=null;
             temp3=null;
             temp4=null;
             System.gc();
          } catch (FileNotFoundException c) {
        	  //an dn uparxei to arxeio kanei load dummy data
        	  System.out.println("new file.");
        	  Admin headAdmin = new Admin("KostasKoukos", "kokaki" , "123");
          	  User.getUsers_Array().add(headAdmin);
              User.getUsers_Array().add(headAdmin.createCustomer("AlexhsVasileiou","alexhs.v","alexhs123","20-09-2003"));
              ContentAdmin headContentAdmin = headAdmin.createContentAdmin("AlexhsVasileiou","alexhs.vc","alexhs123");
              User.getUsers_Array().add(headContentAdmin);
         
              headAdmin.updateUser("kokaki","IoannisKroitor","kroitor","123");
         
              headAdmin.viewAllUsers();
            
              headContentAdmin.insertFilm("Epoxh twn drakwn", "action", "description", true,	Duration.ofMinutes(60), "25/03/2023" );
         
              headContentAdmin.insertFilm("Epoxh twn deinosayrwn", "fantasia", "description", true, Duration.ofMinutes(420), "12/03/2023");
         
              headAdmin.createCinema("aithousa Asteria", false, 120);
              headAdmin.createCinema("aithousa Ilios", false, 120);
              
              headContentAdmin.createProvoli( Film.getFilms_Array().get(0), Cinema.getCinemas_Array().get(0), "123", LocalTime.parse("15:30"), false);
              headContentAdmin.createProvoli( Film.getFilms_Array().get(0), Cinema.getCinemas_Array().get(0), "123", LocalTime.parse("18:30"), false);
          } catch (IOException i) {
             i.printStackTrace();
             return;
          } catch (ClassNotFoundException c) {
             System.out.println("Employee class not found");
             c.printStackTrace();
             return;
          }
    	
    
       
        System.out.println(Film.getFilms_Array().get(1).getFilmTitle());
        
    	System.out.println();
    	
    	
    	//mporoume an theloume na ginoun static oi methodoi opote na mhn theloume object gia na tis kaloume
    	
    	System.out.println(Provoli.getProvoles_Array().size());
    
  	for (Provoli provoli : Provoli.getProvoles_Array()) {
  	    System.out.println(provoli.toString());
  	}
  	
  
      //Serialize
  	try {
             FileOutputStream fileOut = new FileOutputStream("cinepolis.txt");
             ObjectOutputStream out = new ObjectOutputStream(fileOut);
        
             
             out.writeObject(User.getUsers_Array());
             
             out.writeObject(Film.getFilms_Array());
           
             out.writeObject(Cinema.getCinemas_Array());
            
             out.writeObject(Provoli.getProvoles_Array());
             out.close();
             fileOut.close();
             System.out.printf("Serialized data is saved in cinepolis.txt");
          } catch (IOException i) {
             i.printStackTrace();
          }
  	
  }
    
    
    
}