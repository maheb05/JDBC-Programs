package com.tap;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program3 {
	public static void main(String[] args) {
		
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "select * from student";
		
		Connection mycon = null;
		Statement statement = null;
		ResultSet res = null;
		
		
		try {
			mycon = DriverManager.getConnection(url,username,password);	
			statement = mycon.createStatement();
			res = statement.executeQuery(sql);
			
			while(res.next()) {
				int id = res.getInt("id");
				String name = res.getString("name");
				Date dob = res.getDate("dob");
				System.out.println(id+" "+name+" "+dob);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		Program2.finall(mycon,statement,res);
	}

}
