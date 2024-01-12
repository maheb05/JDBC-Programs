package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program6 {
	
	public static Connection mycon;
	public static Statement statement;
	public static ResultSet res;
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql1 = "insert into student values(6,'Avees','2002-06-14')";
		String sql2 = "insert into student values(7,'khaja','2002-06-15')";
		String sql3 = "insert into student values(8,'Basha','2002-06-30')";
		String sql4 = "insert into student values(9,'nasta','2002-06-28')";
		
		 try {
			 mycon = DriverManager.getConnection(url,username,password);
			 Program4.display(mycon, statement, res);
			 statement = mycon.createStatement();
			 statement.addBatch(sql1);
			 statement.addBatch(sql2);
			 statement.addBatch(sql3);
			 statement.addBatch(sql4);
			 
			 int[] arr = statement.executeBatch();
			 for(int i=0;i<=arr.length;i++) {
				 System.out.print(arr[i]+" ");
			 }
			 System.out.println();
			 
			 Program4.display(mycon, statement, null);
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		 
	}
}
