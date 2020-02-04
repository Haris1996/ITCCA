package com;

/**
 * Created by Umar Khatana on 5/28/2019.
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
@ManagedBean(name = "adminBean")
@SessionScoped

public class AdminUser {
    static int id=0;
    public String firstName;
    public String lastName;
    public Date dob;
    public String address;
    public String userType;
    public String email;
    public String ausername;
    public String apassword;

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String contact;


    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }

    public String getAusername() {
        return ausername;
    }

    public void setAusername(String ausername) {
        this.ausername = ausername;
    }



    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag =false;

    public static int getId() {
        return id;
    }



    public AdminUser() {
        id++;
        flag=false;
        try{
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String validateUser()
    {
        String query="SELECT * FROM register WHERE username='"+ausername+"' AND password='"+apassword+"'";
        try{
            rs=stmt.executeQuery(query);
            while(rs.next()){
                flag=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag==true)
            return "main.xhtml?faces-redirect=true";
        else
            return "";
    }

    public String reg()
    {
        return "userRegisterView.xhtml?faces-redirect=true";
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

    pstmt.setString(1, this.getAusername());
    pstmt.setString(2, this.getApassword());

    pstmt.setString(3, this.getFirstName());
    pstmt.setString(4, this.getEmail());
    pstmt.setString(5, this.getAddress());


    pstmt.executeUpdate();
    System.out.println("Success!");
    return "main.xhtml?faces-redirect=true";
}
    {
        String query="INSERT INTO login ( `username`, `email`, `password`, `name`,`contact`,`address`) VALUES (NULL, '"+ausername+"', '"+email+"', '"+apassword+"', 'customer');";
        try{
            int x=stmt.executeUpdate(query);
            if(x>0){
                flag=true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        if(flag==true)
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User Added", "Username: "+ausername+" Email: "+email+""));
        else
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "User Not Added", "Username: "+ausername+" Email: "+email+""));
    }
}
