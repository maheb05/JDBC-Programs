package com.tap;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Program13 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		
		String sql = "update employee1 set dp = ? where id = ?";
		
		try {
			FileInputStream fis = new FileInputStream("C:/Users/hp/eclipse-workspace/jdbcclasses/dp/Alex.jpg");
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection connection = DriverManager.getConnection(url,username,password);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setBinaryStream(1, fis);
			statement.setInt(2, scan.nextInt());
			int i = statement.executeUpdate();
			System.out.println("Rows Effected "+i);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
