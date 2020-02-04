package com;

/**
 * Created by Umar Khatana on 6/17/2019.
 */
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name="vehicleBean")
public class VehicleReport {
  public String date;
    public String time;
    public int vehicle_count;
    public String vehicle_type = "null";
    public String group;
    public String loc_id;
    public String vehicle_direction;
    public String toll_plaza;
    public String toll_line;
    public int tax;
    public String refresh;
    public int ttax;
    public int tvcount;
    List<String> dateList;

    @PostConstruct

    public List<String> getDateList() throws SQLException {

        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public VehicleReport() { }

    public VehicleReport(String date, String time, int vehicle_count, String vehicle_type, String group, String loc_id, String vehicle_direction, String toll_plaza, String toll_line, int tax) {
        this.date = date;
        this.time = time;
        this.vehicle_count = vehicle_count;
        this.vehicle_type = vehicle_type;
        this.group = group;
        this.loc_id = loc_id;
        this.vehicle_direction = vehicle_direction;
        this.toll_plaza = toll_plaza;
        this.toll_line = toll_line;
        this.tax = tax;
    }

    public int getTvcount() throws SQLException {
        getVehicleCount();
        return tvcount;
    }

    public void setTvcount(int tvcount) {
        this.tvcount = tvcount;
    }

    public int getTtax() throws SQLException {
        getVehicleTax();
        return ttax;
    }

    public void setTtax(int ttax) {
        this.ttax = ttax;
    }

    public String getRefresh() throws SQLException {
        getVehicleList();
        getShortVehicleList();
        getVehicle_count();
        getVehicleTax();
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getVehicle_count(){
        return vehicle_count;
    }

    public void setVehicle_count(int vehicle_count) {
        this.vehicle_count = vehicle_count;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getLoc_id() {
        return loc_id;
    }

    public void setLoc_id(String loc_id) {
        this.loc_id = loc_id;
    }

    public String getVehicle_direction() {
        return vehicle_direction;
    }

    public void setVehicle_direction(String vehicle_direction) {
        this.vehicle_direction = vehicle_direction;
    }

    public String getToll_plaza() {
        return toll_plaza;
    }

    public void setToll_plaza(String toll_plaza) {
        this.toll_plaza = toll_plaza;
    }

    public String getToll_line() {
        return toll_line;
    }

    public void setToll_line(String toll_line) {
        this.toll_line = toll_line;
    }

    public int getTax() throws SQLException {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }








    //Count Vehicles


    public int getVehicleCount() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        //System.out.println("Vehicle coumt kr yaar");

        List<VehicleReport> lister = new ArrayList<VehicleReport>();
        PreparedStatement pstmt = connection.prepareStatement("select COUNT(tax) AS 'vehi'from report");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            VehicleReport veh=new VehicleReport();
            tvcount = rs.getInt("vehi");
            lister.add(veh);
        }

        return tvcount;
    }


    public List<VehicleReport> getVehicleList() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");

        List<VehicleReport> list = new ArrayList<VehicleReport>();
        PreparedStatement pstmt;
        if (vehicle_type == "null") {
             pstmt = connection.prepareStatement("select * from report ORDER BY `report`.`time` DESC LIMIT 20 ");
        }
        else {
             pstmt = connection.prepareStatement("select * from report WHERE date = '"+date+"' AND vehicle_t = '"+vehicle_type+"' AND toll_plaza = '"+toll_plaza+"' ORDER BY `report`.`time` DESC LIMIT 20 ");
        }
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            date = rs.getString("date");
            time = rs.getString("time");
            vehicle_count = rs.getInt("vehicle_c");
            vehicle_type = rs.getString("vehicle_t");
            group = rs.getString("group");
            loc_id = rs.getString("location");
            vehicle_direction = rs.getString("vehicle_dir");
            toll_plaza=rs.getString("toll_plaza");
            toll_line=rs.getString("toll_line");
            tax=rs.getInt("tax");

            list.add(new VehicleReport(date,time,vehicle_count,vehicle_type,group,loc_id,vehicle_direction,toll_plaza,toll_line,tax));
        }
        return list;
    }


    public List<VehicleReport> getShortVehicleList() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        System.out.println("Opened database successfully");

        List<VehicleReport> list = new ArrayList<VehicleReport>();
        String a = "Suv";
        PreparedStatement pstmt = connection.prepareStatement("select * from report WHERE vehicle_t = '"+a+"' ORDER BY `report`.`time` DESC LIMIT 10");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            date = rs.getString("date");
            time = rs.getString("time");
            vehicle_count = rs.getInt("vehicle_c");
            vehicle_type = rs.getString("vehicle_t");
            group = rs.getString("group");
            loc_id = rs.getString("location");
            vehicle_direction = rs.getString("vehicle_dir");
            toll_plaza=rs.getString("toll_plaza");
            toll_line=rs.getString("toll_line");
            tax=rs.getInt("tax");

            list.add(new VehicleReport(date,time,vehicle_count,vehicle_type,group,loc_id,vehicle_direction,toll_plaza,toll_line,tax));
        }
        return list;
    }

    //Count Tax

    public String getDateLister() throws SQLException {
        getVehicleList();
        return "supervisorReport.xhtml?faces-redirect=true";
    }



    public int getVehicleTax() throws SQLException {

        String driverName = "com.mysql.jdbc.Driver";


        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fyp", "root", "");
        //System.out.println("tax count kr0");

        List<VehicleReport> lister = new ArrayList<VehicleReport>();
        PreparedStatement pstmt = connection.prepareStatement("select SUM(tax) AS 'tx'from report");
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            VehicleReport veh = new VehicleReport();
            ttax = rs.getInt("tx");
            lister.add(veh);
        }
        return ttax;
    }
    }
