package com.tap;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program4 {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		Connection mycon = DriverManager.getConnection(url,username,password);
		Statement statement = null;
		ResultSet res = null;
		display(mycon,statement,res);
	}

	public static void display(Connection mycon, Statement statement,ResultSet res) throws SQLException {
		String sql = "SELECT * from `student`";
		
		statement = mycon.createStatement();
		res = statement.executeQuery(sql);
		
		while(res.next()) {
			int id = res.getInt("id");
			String name = res.getString("name");
			String dob = res.getString("dob");
			
			System.out.println(id+" "+name+" "+dob);
		}
	}
}
