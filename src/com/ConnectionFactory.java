package com;

/**
 * Created by Umar Khatana on 5/28/2019.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    Connection c=null;
    public ConnectionFactory() {
        try {
            String url = "jdbc:mysql://localhost:3306/fyp";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");

            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url, info);

            if (c != null) {
                System.out.println("Successfully connected to MySQL database test");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL databse");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/fyp";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "");

            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection(url, info);

            if (c != null) {
                System.out.println("Successfully connected to MySQL database test");
            }

        } catch (SQLException ex) {
            System.out.println("An error occurred while connecting MySQL databse");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return c;

    }

}

