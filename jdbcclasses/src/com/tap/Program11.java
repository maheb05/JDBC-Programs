package com.tap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Program11 {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		
		Scanner scan = new Scanner(System.in);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection mycon = DriverManager.getConnection(url,username,password);
			CallableStatement call = mycon.prepareCall("{call emp_count(?)}");
			System.out.println("Enter Salary:");
			call.setInt(1, scan.nextInt());
			call.registerOutParameter(1, Types.INTEGER);
			call.execute();
			int count = call.getInt(1);
			System.out.println(count);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
