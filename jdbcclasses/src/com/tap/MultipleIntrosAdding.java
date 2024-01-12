package com.tap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MultipleIntrosAdding {
	
	static Connection mycon = null;
	static PreparedStatement statement = null;
	static int count = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/first";
		String username = "root";
		String password = "root";
		String sql = "update employee1 set intro=? where id=?";
		try {
			int n;
			Class.forName("com.mysql.cj.jdbc.Driver");
			mycon = DriverManager.getConnection(url,username,password);
		do {
				System.out.println(" press 1 if you to upload more ");
				System.out.println(" press 2 if you to stop ");
				n = scan.nextInt();
				
				if(n==1) {
					
					System.out.println("Enter introducation path :");
					String path = scan.next();
					File f = new File(path);
					if(!(f.exists())) {
						System.out.println("Invalid path");
						continue;
					}
					FileReader fr = new FileReader(path);
					statement = mycon.prepareStatement(sql);
					statement.setCharacterStream(1, fr);
					System.out.println("Enter Id ");
					statement.setInt(2, scan.nextInt());
					int i = statement.executeUpdate();
					System.out.println(i+"Rows Updated");
					count++;
				}
				if(n==2) {
					System.out.println("Thank you");
					break;
				}
		}
			while(true);	
		System.out.println("Rows Updated "+count);
		} 
		catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}

