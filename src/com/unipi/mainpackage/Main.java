package com.unipi.mainpackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	public static ArrayList<User> Users_Array = new ArrayList<User>(); //holds basic information about user -> username, password,type(admin,customer,contentadmin),name
	public static ArrayList<Film> Films_Array = new ArrayList<Film>();
	public static ArrayList<Cinema> Cineams_Array = new ArrayList<Cinema>();
	public static int FilmSum=0;
    public static void main(String[] args) {
	 // new GUI(); to start gui -- later
    	
    	//on first run ever
    	
    	
    	Admin headAdmin = CreateAdmin("KostasKoukos", "kokaki" , "eimaigay123");
    	
    	
    	Users_Array.add(headAdmin.createCustomer("AlexhsVasileiou","alexhs.v","alexhs123","20-09-2003"));
    	ContentAdmin headContentAdmin = headAdmin.createContentAdmin("AlexhsVasileiou","alexhs.vc","alexhs123");
    	Users_Array.add(headContentAdmin);
    	
        headAdmin.updateUser("kokaki","IoannisKroitor","kroitor","eimaistraight123", Users_Array);
     
        headAdmin.viewAllUsers(Users_Array);
        
        headContentAdmin.insertFilm(FilmSum,"Epoxh tou kroitor", "erotiki", "O megas kroitor se nees peripeties me fasaies", true,	69, Films_Array);
        FilmSum++;
        headContentAdmin.insertFilm(FilmSum,"Epoxh tou vasileiou", "fantasia", "O megas alexhs v se nees peripeties me fasaious", true,	420, Films_Array);
        FilmSum++;
        System.out.println(Films_Array.get(1).getTitle());
    	System.out.println(FilmSum);
    }
    
    
    public static Admin CreateAdmin(String name, String username, String password) {  //gia otan valoume database
    	
    	
    	Admin Admin = new Admin("KostasKoukos", "kokaki" , "eimaigay123");
    	Users_Array.add(Admin);
    	return Admin;
    }
}
