package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class AddingImages {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/first";
        String username = "root";
        String password = "root";
        String sql = "UPDATE employee1 SET dp=? WHERE id=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection mycon = DriverManager.getConnection(url, username, password);

            while (true) {
                System.out.println("Press 1 to Enter Images");
                System.out.println("Press 2 to exit");
                int n = scan.nextInt();

                if (n == 2) {
                	System.out.println("THANK YOU ");
                    break; // Exit the loop if the user enters "2".
                } else if (n == 1) {
                    System.out.println("Enter Image Path:");
                    String imagePath = scan.next();
                    File file = new File(imagePath);

                    if (!file.exists()) {
                        System.out.println("File does not exist. Enter a valid path.");
                        continue;
                    }

                    try (FileInputStream fis = new FileInputStream(file);
                         PreparedStatement statement = mycon.prepareStatement(sql)) {

                        statement.setBinaryStream(1, fis);
                        System.out.println("Enter ID for the image:");
                        statement.setInt(2, scan.nextInt());

                        int i = statement.executeUpdate();
                        System.out.println("Images Updated: " + i);
                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid option. Please enter 1 or 2.");
                }
            }

            mycon.close(); // Close the database connection when done.
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
