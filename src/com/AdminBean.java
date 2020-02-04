package com;

/**
 * Created by Umar Khatana on 5/27/2019.
 */

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.sql.*;

@ManagedBean(name = "aBean")
public class AdminBean {


    public AdminBean() {
    }

    Admin a;

    public void setA(Admin a) {
        this.a = a;
    }

    public Admin getA() {
        return a;
    }

    @PostConstruct
    public void init() {
        a = new Admin();
    }


    //  Login Method

    public String Login(Admin a) {
        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {


            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
            ResultSet rs = null;

            PreparedStatement pst =  connection.prepareStatement("SELECT  from login where username = ? and password = ?");

            rs = pst.executeQuery();

            if (rs.next()) {
                // pst.setString(1, this.a.getA_username());
                //pst.setString(2, this.a.getA_password());

                //result found, means valid inputs
                System.out.println("Succeeded");
                //return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());


        }

        return "main";
    }

    public String Register() throws SQLException {
        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO register(username,password,fname,email,address) VALUES (?,?,?,?,?)");

        pstmt.setString(1, this.a.getA_username());
        pstmt.setString(2, this.a.getA_password());

        pstmt.setString(3, this.a.getA_name());
        pstmt.setString(4, this.a.getA_email());
        pstmt.setString(5, this.a.getA_address());


        pstmt.executeUpdate();
        System.out.println("Success!");
        return "main.xhtml?faces-redirect=true";
    }
}
