package com.tap;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Program12 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url,username,password);
			CallableStatement call = connection.prepareCall("{call get_emps(?)}");
			System.out.println("Enter Salary:");
			call.setInt(1, scan.nextInt());
			call.execute();
			ResultSet res = call.getResultSet();
			
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				String mail = res.getString("mail");
				String department = res.getString("department");
				int salary = res.getInt("salary");
				
				System.out.printf("%-5d %-8s %-12s %-8s %-6d",id,name,mail,department,salary);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
