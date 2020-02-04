package com;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.util.Map;

/**
 * Created by Umar Khatana on 10/23/2019.
 */

@ManagedBean(name = "clerkBean")
@SessionScoped
public class ClerkBean {

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    Connection connection;


    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ManagerBean.id = id;
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

    public ClerkBean() {

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



    //Manager login here
    public String validateClerk() {
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
            return "clerk.xhtml?faces-redirect=true";
        else
            return "";
    }




}
