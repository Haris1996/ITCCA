package com;

import com.sun.faces.context.SessionMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by Umar Khatana on 6/7/2019.
 */

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    Connection connection;



    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserBean.id = id;
    }

    static int id = 0;


    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag = false;


    // public SessionMap<String,Object> sessionMap;
    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    User u;

    public UserBean() {

        id++;
        flag = false;
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        u = new User();
    }



    //Supervisor login here
    public String validateUser() {
        String query = "SELECT * FROM userdetail WHERE username='" + u.u_username + "' AND password='" + u.u_password + "' AND designation='"+u.u_designation+"'";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true)
            return "supervisor.xhtml?faces-redirect=true";
        else
            return "";
    }



    //Supervisor Update Password here
    public String updatePassword() {
        String query = "update userdetail set password=? where username='"+u.u_username+"'";
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (flag == true)
            return "supervisor.xhtml?faces-redirect=true";
        else
            return "";
    }



    public String SaveToDB() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO userdetail(username,password,jobid,name,fathername,email,contact,gender,designation,jobplace,dob,address) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");

        pstmt.setString(1, this.u.getU_username());
        pstmt.setString(2, this.u.getU_password());

        pstmt.setString(3, this.u.getJobId());
        pstmt.setString(4, this.u.getU_name());
        pstmt.setString(5, this.u.getU_fatherName());
        pstmt.setString(6, this.u.getU_email());
        pstmt.setString(7, this.u.getU_phone());
        pstmt.setString(8, this.u.getU_gender());
        pstmt.setString(9, this.u.getU_designation());
        pstmt.setString(10, this.u.getU_jobPlace());
        pstmt.setString(11, this.u.getU_dateOfBirth());
        pstmt.setString(12, this.u.getU_address());
        pstmt.executeUpdate();
        System.out.println("Success!");
        return "main2.xhtml?faces-redirect=true";
    }

    public List<User> getUserList() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");

        List<User> list = new ArrayList<User>();
        PreparedStatement pstmt = connection.prepareStatement("select * from userdetail");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.u_username = rs.getString("username");
            u.u_password = rs.getString("password");
            u.jobId = rs.getString("jobid");
            u.u_name = rs.getString("name");
            u.u_fatherName = rs.getString("fathername");

            u.u_email = rs.getString("email");
            u.u_phone = rs.getString("contact");
            u.u_gender = rs.getString("gender");

            u.u_designation = rs.getString("designation");

            u.u_jobPlace = rs.getString("jobplace");
            u.u_dateOfBirth = rs.getString("dob");

            u.u_address = rs.getString("address");
            list.add(u);
        }
        return list;
    }


//Supervisor Manage Managers
    public List<User> getManagerList() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");

        List<User> list = new ArrayList<User>();
        PreparedStatement pstmt = connection.prepareStatement("select * from userdetail where designation='Manager'");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            User u = new User();
            u.u_username = rs.getString("username");
            u.u_password = rs.getString("password");
            u.jobId = rs.getString("jobid");
            u.u_name = rs.getString("name");
            u.u_fatherName = rs.getString("fathername");

            u.u_email = rs.getString("email");
            u.u_phone = rs.getString("contact");
            u.u_gender = rs.getString("gender");

            u.u_designation = rs.getString("designation");


            u.u_jobPlace = rs.getString("jobplace");
            u.u_dateOfBirth = rs.getString("dob");
            u.u_address = rs.getString("address");
            list.add(u);
        }
        return list;
    }





/*
    public String EditManager(){
u.setEditable(true);
        return null;
    }*/

    //Method for delete Manager
    public String DeleteUser(User u) throws SQLException {
        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");


        List<User> list = new ArrayList<User>();
        String username = u.u_username;
        String password = u.u_password;
        String jobId = u.jobId;
        String name = u.u_name;
        String fathername = u.u_fatherName;
        String email = u.u_email;
        String phone = u.u_phone;
        String gender = u.u_gender;
        String designation = u.u_designation;
        String jobplace = u.u_jobPlace;
        String dob = u.u_dateOfBirth;
        String address = u.u_address;

        String sql = "delete from userdetail where name='" + name + "'" + "AND email='" + email + "'";
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        list.remove(u);
        System.out.print("Success!");
        return null;
    }

    public String Edit() throws SQLException{

        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String field_jobId= params.get("action");
        User u;

        String driverName = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");

            String sql=("select * from userdetail where jobid = " + field_jobId);
                   System.out.println("Edit function");
            Statement st = connection.createStatement();
            System.out.println(sql);

            ResultSet rs = st.executeQuery(sql);
            rs.next();
            u=new User();
            u.setU_username(rs.getString("username"));
            u.setU_password(rs.getString("password"));
            u.setJobId(rs.getString("jobid"));
            u.setU_name(rs.getString("name"));
            u.setU_fatherName(rs.getString("fathername"));
            u.setU_email(rs.getString("email"));
            u.setU_phone(rs.getString("contact"));
            u.setU_gender(rs.getString("gender"));
            u.setU_designation(rs.getString("designation"));
            u.setU_jobPlace(rs.getString("jobplace"));
            u.setDateOfBirth(rs.getDate("dob"));
            u.setU_address(rs.getString("address"));
            sessionMap.put("EditUser", u);
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return "/edit.xhtml?faces-redirect=true";
    }



    public String Update(User u) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String	update_jobId= params.get("update_jobId");
        String driverName = "com.mysql.jdbc.Driver";
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
            System.out.println("Update database successfully");
                PreparedStatement ps = connection.prepareStatement("update userdetail set username=?,password=?,name=?,fathername=?,email=?,contact=?,gender=?,designation=?,jobplace=?,dob=?,address=?, WHERE jobid=?");
                ps.setString(1, this.u.getU_username());
                ps.setString(2, this.u.getU_password());
                ps.setString(3, update_jobId);
                ps.setString(4, this.u.getU_name());
                ps.setString(5, this.u.getU_fatherName());
                ps.setString(6, this.u.getU_email());
                ps.setString(7, this.u.getU_phone());
                ps.setString(8, this.u.getU_gender());
                ps.setString(9, this.u.getU_designation());
                ps.setString(10, this.u.getU_jobPlace());
                ps.setString(11, this.u.getU_dateOfBirth());
                ps.setString(12, this.u.getU_address());
                System.out.println(ps);
                ps.executeUpdate();
                connection.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            return "/main2.xhtml?faces-redirect=true";
        }
}


