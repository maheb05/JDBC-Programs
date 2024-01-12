package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Program1 {
	public static void main(String[] args)  {
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username="root";
		String password="root";
		try {
			Connection myCon = DriverManager.getConnection(url,username,password);
			System.out.println("Connection Established");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
