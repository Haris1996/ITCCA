package com;

/**
 * Created by Umar Khatana on 6/1/2019.
 */
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class User {

    public User() {
        flag = false;
        try {
            con = new ConnectionFactory().getConnection();
            stmt = con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    Connection con = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    boolean flag = false;


    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getU_username() {
        return u_username;
    }

    public void setU_username(String u_username) {
        this.u_username = u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public String u_username;
    public String u_password;
    public String jobId;
    public String u_name;
    public String u_fatherName;
    public String u_email;
    public String u_phone;
    public String u_gender;
    private boolean editable;

public boolean isEditable(){
    return editable;
}
    public void setEditable(boolean editable){
        this.editable=editable;
    }



    public String getU_designation() {
        return u_designation;
    }

    public void setU_designation(String u_designation) {
        this.u_designation = u_designation;
    }

    public String u_designation;

    public String getU_dateOfBirth() {
        return u_dateOfBirth;
    }

    public void setU_dateOfBirth(String u_dateOfBirth) {
        this.u_dateOfBirth = u_dateOfBirth;
    }

    Date dateOfBirth;


    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_fatherName() {
        return u_fatherName;
    }

    public void setU_fatherName(String u_fatherName) {
        this.u_fatherName = u_fatherName;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_gender() {
        return u_gender;
    }

    public void setU_gender(String u_gender) {
        this.u_gender = u_gender;
    }




    public String getU_address() {
        return u_address;
    }

    public void setU_address(String u_address) {
        this.u_address = u_address;
    }


    public String getU_jobPlace() {
        return u_jobPlace;
    }

    public void setU_jobPlace(String u_jobPlace) {
        this.u_jobPlace = u_jobPlace;
    }

    public String u_jobPlace;
    public String u_dateOfBirth;
    public String u_address;



    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}