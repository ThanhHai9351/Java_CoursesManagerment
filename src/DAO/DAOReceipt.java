/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Course;
import MODEL.Receipt;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DAOReceipt {
    Connection con=ConnectDB.connect();
    public List<Receipt> getAllReceipt(){
        List<Receipt> list=new ArrayList<>();
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT dbo.Account.Name,dbo.Course.Name,Price,StartDay FROM dbo.RegisterCourse,dbo.Course,dbo.CourseSchedule,dbo.Account WHERE dbo.RegisterCourse.IDCourseSchedule=dbo.CourseSchedule.ID AND dbo.CourseSchedule.IDCourse=dbo.Course.ID AND dbo.Account.ID=IDStudent AND IsPayment=1");
            while(resultSet.next()){
                String name =resultSet.getString(1);
                String nameCourse=resultSet.getString(2);
                float price=resultSet.getFloat(3);
                Date startDay=resultSet.getDate(4);
                list.add(new Receipt(name, nameCourse, price, startDay));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
