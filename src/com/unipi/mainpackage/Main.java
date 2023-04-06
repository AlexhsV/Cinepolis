package com.unipi.mainpackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	
    public static void main(String[] args) {
	 // new GUI(); to start gui -- later
    	
    	//on first run ever
    	
    	
    	Admin headAdmin = new Admin("KostasKoukos", "kokaki" , "eimaigay123");
    	User.getUsers_Array().add(headAdmin);
    	
    	
    	
    	User.getUsers_Array().add(headAdmin.createCustomer("AlexhsVasileiou","alexhs.v","alexhs123","20-09-2003"));
    	ContentAdmin headContentAdmin = headAdmin.createContentAdmin("AlexhsVasileiou","alexhs.vc","alexhs123");
    	User.getUsers_Array().add(headContentAdmin);
    	
        headAdmin.updateUser("kokaki","IoannisKroitor","kroitor","eimaistraight123");
     
        headAdmin.viewAllUsers();
        
        headContentAdmin.insertFilm("Epoxh tou kroitor", "erotiki", "O megas kroitor se nees peripeties me fasaies", true,	69, "25/03/2023" );
        
        headContentAdmin.insertFilm("Epoxh tou vasileiou", "fantasia", "O megas alexhs v se nees peripeties me fasaious", true,420,"12/03/2023");
        
        headAdmin.createCinema("aithousa Asteria", false, 120);
       
        System.out.println(Film.getFilms_Array().get(1).getFilmTitle());
        
    	System.out.println();
    	
    	
    	//mporoume an theloume na ginoun static oi methodoi opote na mhn theloume object gia na tis kaloume
    	headContentAdmin.createProvoli(1, Film.getFilms_Array().get(1), Cinema.getCinemas_Array().get(1), ", null);
    }
    
    
}
