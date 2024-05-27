/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Mail;
import MODEL.Receipt;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DAOMail {
    Connection con=ConnectDB.connect();
    public List<Mail> getAllMail(){
        List<Mail> list=new ArrayList<>();
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT dbo.Mail.*, Name FROM dbo.Mail,dbo.Account WHERE dbo.Mail.IDAccount=dbo.Account.ID");
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                int idAccount=resultSet.getInt(2);
                String title=resultSet.getString(3);
                String message=resultSet.getString(4);
                boolean isRead=resultSet.getBoolean(5);
                String name=resultSet.getString(6);
                list.add(new Mail(id, idAccount, name, title, message, isRead));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public void upDateRead(int id) throws SQLException{
        String sql="UPDATE dbo.Mail SET IsRead=1 WHERE ID=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeLargeUpdate();
    }
    public void delete(int id) throws SQLException{
        String sql="DELETE FROM dbo.Mail WHERE ID=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeLargeUpdate();
    }
}
