/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ConnectDB.connect;
import MODEL.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
