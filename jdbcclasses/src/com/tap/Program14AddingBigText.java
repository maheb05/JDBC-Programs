package com.tap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Program14AddingBigText {
	
	static Connection mycon = null;
	static PreparedStatement statement = null;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "update employee1 set intro=? where id=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
			
			FileReader fr = new FileReader("C:\\Users\\hp\\eclipse-workspace\\jdbcclasses\\dp\\alexIntro");
			statement = mycon.prepareStatement(sql);
			statement.setCharacterStream(1, fr);
			System.out.println("Enter id to update Intro ");
			statement.setInt(2, scan.nextInt());
			int i = statement.executeUpdate();
			System.out.println(i+" Intro's Updated");
		} catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(mycon != null) {
					mycon.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
