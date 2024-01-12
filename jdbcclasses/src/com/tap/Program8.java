package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Program8 {
	
	static Connection mycon = null;
	static PreparedStatement statement2 = null;
	
	static String sql = "INSERT INTO `employee1` (`id`,`name`,`mail`,`department`,`salary`) values(? ,?, ?, ?, ?)";
	static String sql1 = "UPDATE EMPLOYEE1 set name=?, mail=?, department=?,salary=? where id = ?";
	static String sql2 = "DELETE from EMPLOYEE1 where id=?";
	static String s = null;
	
	static int n ;
	
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
			
			do {
				
				System.out.println("Choose Operation ");
				System.out.println("1 INSERT");
				System.out.println("2 UPDATE");
				System.out.println("3 DELETE");
				System.out.println("4 EXIT");
				n = scan.nextInt();
				
				if(n==1) {
					insert();
				}
				
				if(n==2) {
					update();
				}
				
				if(n==3) {
					delete();
				}
				if(n==4) {
					System.out.println("THANK YOU");
				}
			}
			while(n != 4);
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	static public void insert() throws SQLException {
		
		do {
			
			statement2 = mycon.prepareStatement(sql);
			System.out.println("Enter ID:");
			statement2.setInt(1, scan.nextInt());
			System.out.println("Enter Name:");
			statement2.setString(2, scan.next());
			System.out.println("Enter Mail:");
			statement2.setString(3, scan.next());
			System.out.println("Enter Department:");
			statement2.setString(4,scan.next());
			System.out.println("Enter Salary:");
			statement2.setInt(5, scan.nextInt());
			
			System.out.println("Do you want to Enter More?? (yes/no)");
			s = scan.next();
			
			statement2.addBatch();
		}
		while(s.equals("yes"));
		
		statement2.executeBatch();
		
	}
	
	static public void update() throws SQLException {
		
		statement2 = mycon.prepareStatement(sql1);
		do {
			System.out.println("Enter Name to Update:");
			statement2.setString(1, scan.next());
			System.out.println("Enter Mail to Update:");
			statement2.setString(2, scan.next());
			System.out.println("Enter Department to update:");
			statement2.setString(3, scan.next());
			System.out.println("Enter Salary to Update");
			statement2.setInt(4, scan.nextInt());
			System.out.println("Enter Employee id for whom you want to Update:");
			statement2.setInt(5, scan.nextInt());
			
			System.out.println("Do you want to Update More?? (yes/no)");
			s = scan.next();
			
			statement2.addBatch();
		}
		while(s.equals("yes"));
		
		statement2.executeBatch();
		
		System.out.println("Changes Successfull...");
	}
	
	static void delete() throws SQLException {
		
		statement2 = mycon.prepareStatement(sql2);
		
		do {
			
			System.out.println("Enter Employee id for whom you want to Delete:");
			statement2.setInt(1, scan.nextInt());
			
			System.out.println("Do you want to Delete More?? (yes/no)");
			s = scan.next();
			
			statement2.addBatch();
		}
		while(s.equals("yes"));
		
		statement2.executeBatch();
		
		System.out.println("Changes Successfull...");
	}
}
