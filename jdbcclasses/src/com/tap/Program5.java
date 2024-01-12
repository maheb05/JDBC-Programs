package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program5 {
	
	public static Connection mycon;
	public static Statement statement;
	public static ResultSet res;
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "delete from student where name = 'Avees'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			mycon = DriverManager.getConnection(url,username,password);
			Program4.display(mycon, statement, res);
			statement = mycon.createStatement();
			int i = statement.executeUpdate(sql);
			System.out.println(i);
			System.out.println();
			Program4.display(mycon, statement, null);
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
