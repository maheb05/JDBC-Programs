package com.tap;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Program2 {
	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "select * from employee";
		
		Connection mycon = null;
		Statement statement= null;
		ResultSet res = null;
		
		try {
			mycon = DriverManager.getConnection(url,username,password);
			statement = mycon.createStatement();
			res = statement.executeQuery(sql);
			
			while(res.next()) {
				int e_id = res.getInt("e_id");
				String e_name = res.getString("e_name");
				String e_email = res.getString("e_email");
				Date e_dob = res.getDate("e_dob");
				int e_number = res.getInt("e_number");
				System.out.println(e_id+" "+e_name+" "+e_email+" "+e_dob+" "+e_number);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(res != null) {
					mycon.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(statement != null) {
					statement.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(res != null) {
					res.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void finall(Connection mycon,Statement statement,ResultSet res) {
			
		try {
			if(mycon != null) {
				mycon.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(statement != null) {
				statement.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(res != null) {
				res.close();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}