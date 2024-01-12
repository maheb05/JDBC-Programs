package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Program7 {
	static Connection mycon = null;
	//static Statement statement1 = null;
	static PreparedStatement statement = null;
	static ResultSet res = null;
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "update student set dob = ? where id=11";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
			Program4.display(mycon, statement, res);
			statement = mycon.prepareStatement(sql);
			String s=scan.nextLine();
			statement.setString(1,s);
			int i = statement.executeUpdate();
			System.out.println(i);
			Program4.display(mycon, statement, res);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
//		finally {
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (mycon != null) {
//                    mycon.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
		
	}
}
