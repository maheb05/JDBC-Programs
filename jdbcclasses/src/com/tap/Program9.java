package com.tap;

import java.sql.*;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.*;

public class Program9 {
	
	static Connection mycon = null;
	static PreparedStatement statement2 = null;
	static Statement statement1 = null;
	static ResultSet res = null;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
			result(mycon,statement1,res);
			transaction();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void transaction() throws SQLException {
		mycon.setAutoCommit(false);
		System.out.println("Enter sender Name:");
		String sender = scan.next();
		System.out.println("Enter receiver Name:");
		String receiver = scan.next();
		System.out.println("Enter Amount");
		int amount = scan.nextInt();
		
		int i = update(sender,-amount);
		int j = update(receiver,amount);
		boolean rese = isConfirm(i,j);
		
		if(rese == true) {
			mycon.commit();
			System.out.println("Tranaction successfull.....");
		}
		else {
			mycon.rollback();
			System.out.println("Transaction Failed.....");
		}
		
		result(mycon,statement1,res);
	}


	 private static boolean isConfirm(int i,int j) {
		 System.out.println("Do you want to proceed ??? (Yes/No) ");
		 String choice = scan.next();
		 
		 return choice.equalsIgnoreCase("yes") && i==1 && j==1;
	}


	static int update(String sender,int amount) throws SQLException {
		
		String sql = "update employee1 set salary = salary + ? where name = ?";
		statement2 = mycon.prepareStatement(sql);
		statement2.setInt(1, amount);
		statement2.setString(2, sender);
		int i = statement2.executeUpdate();
		return i;
	}
	
	static void result(Connection mycon,Statement statement1,ResultSet res) throws SQLException {
		String sql1 = "Select * from employee1";
		statement1 = mycon.createStatement();
		res = statement1.executeQuery(sql1);
		
		System.out.println("________________________________________________________________________");
		while(res.next()) {
			int id = res.getInt("id");
			String name = res.getString("name");
			String mail = res.getString("mail");
			String department = res.getString("department");
			int salary = res.getInt("salary");
			
			System.out.printf("| %-2d | %-6s | %-20s | %-20s | %-8d | \n",id,name,mail,department,salary);
		}
		System.out.println("________________________________________________________________________");
	}
}
