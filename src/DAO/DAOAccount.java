/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ConnectDB.connect;
import MODEL.Account;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author pc
 */
public class DAOAccount {
//    String connectUrl = "jdbc:sqlserver://THANHHAI:1433;databaseName=CoursesManagerment;integratedSecurity = true;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8";
    Connection connection = connect();

     
     public ArrayList<Account> getAllDataAccount() {
        ArrayList<Account> data = new ArrayList<>();

        try  {
            String sql = "SELECT * FROM Account";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                Date dob = resultSet.getDate("DoB");
                Boolean gender = resultSet.getBoolean("Gender");
                String address = resultSet.getString("Address");
                String idcard = resultSet.getString("IDCard");
                String phone = resultSet.getString("Phone");
                int role = resultSet.getInt("Role");
                String pass = resultSet.getString("Password");
                

                Account item = new Account(id, name.trim(), dob, phone.trim(), idcard.trim(), address.trim(), gender, pass, role);

                data.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
     
      public boolean editDataAccount(int id, String name, Date date, String phone, String address, String cccd, boolean gender) {
         java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String sql = "UPDATE Account SET Name = ?, DoB = ?, Phone = ?, Address = ?, IDCard = ?, Gender = ? WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2,  sqlDate);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setString(5, cccd);
            statement.setBoolean(6, gender);
            statement.setInt(7, id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
      
       public boolean deleteDataAccount(int id) {
        try {
            String sql = "DELETE Account WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
       }
        
        public boolean createDataAccount(String name, Date date, String phone, String address, String cccd, boolean gender) {
        try {
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            String sql = "INSERT INTO Account (Name, DoB, Phone, Address, IDCard, Gender,Role,Password) VALUES (?, ?, ?, ?, ?, ?,2,'123')";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, sqlDate);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.setString(5, cccd);
            statement.setBoolean(6, gender);

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
