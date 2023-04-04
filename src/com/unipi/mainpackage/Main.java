package com.unipi.mainpackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {

	

    public static void main(String[] args) {
	 // new GUI(); to start gui -- later
    	
    	//on first run ever
    	Admin headAdmin = new Admin("KostasKoukos", "kokaki" , "eimaigay123");
    	
    	headAdmin.createUser("ContentAdmin");
    	
    	
    }
}
