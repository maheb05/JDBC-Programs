package com.tap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Program10 {
	static Connection mycon  = null;
	static CallableStatement call = null;
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
			call = mycon.prepareCall("{call dept_count(?,?)}");
			System.out.println("Enter DepartMent Name :");
			call.setString(1,scan.next());
			call.registerOutParameter(2, Types.INTEGER);
			call.execute();
			int count = call.getInt(2);
			System.out.println(count);
//			if(count == 0) {
//				System.out.println("Enter valid Department ");
//			}
//			else {
//				System.out.println(count);
//			}
		}
		catch(SQLException | ClassNotFoundException e) {
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
			
			try {
				if(call != null) {
					call.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
