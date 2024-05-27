/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Teacher;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class DAOTeacher {
    Connection con=ConnectDB.connect();
    public List<Teacher> getAllTeacher() throws SQLException{
        List<Teacher> list=new ArrayList<>();
        Statement statement=con.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT * FROM dbo.Account where Role=1");
        while(resultSet.next()){
            int id=resultSet.getInt(1);
            String name=resultSet.getString(2);
            Date dob=resultSet.getDate(3);
            boolean gender=resultSet.getBoolean(4);
            String address=resultSet.getString(5);
            String idCard=resultSet.getString(6);
            String phone=resultSet.getString(7);
            int role=resultSet.getInt(8);
            String password=resultSet.getString(9);
            //System.out.println(name+" "+address+" "+phone);
            list.add(new Teacher(id, name, dob, gender, address, idCard, phone, role, password));
        }
        return list;
    }
    public List<String> getNameTeacher() throws SQLException{
        List<String> list=new ArrayList<>();
        Statement statement=con.createStatement();
        ResultSet resultSet=statement.executeQuery("SELECT Name FROM dbo.Account where Role=1");
        while(resultSet.next()){
            String name=resultSet.getString(1);
            list.add(name);
        }
        return list;
    }
    public void insertTeacher(String name,Date dob,boolean gender, String address, String idCard,String phone) throws SQLException{
        String sql="INSERT INTO dbo.Account(Name,DoB,Gender,Address,IDCard,Phone,Role,Password)VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setString(1, name);
        statement.setDate(2, dob);
        statement.setBoolean(3, gender);
        statement.setString(4, address);
        statement.setString(5, idCard);
        statement.setString(6, phone);
        statement.setInt(7,1);
        statement.setString(8, "123");
        statement.executeUpdate();
    }
    public void updateTeacher(String name,Date dob,boolean gender, String address, String idCard,String phone) throws SQLException{
        String sql="UPDATE dbo.Account SET Name=?, DoB=?, Gender=?, Address=?, Phone=? WHERE IDCard=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setString(1, name);
        statement.setDate(2, dob);
        statement.setBoolean(3, gender);
        statement.setString(4, address);
        statement.setString(6, idCard);
        statement.setString(5, phone);
        statement.executeUpdate();
    }
    public void deleteTeacher(String IdCard) throws SQLException{
        String sql="DELETE dbo.Account WHERE IDCard=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setString(1, IdCard);
        statement.executeLargeUpdate();
    }
}
