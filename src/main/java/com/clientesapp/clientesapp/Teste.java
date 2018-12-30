package com.clientesapp.clientesapp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
		Date date = new Date(); 
		System.out.println(dateFormat.format(date)); 
		
	}

}
