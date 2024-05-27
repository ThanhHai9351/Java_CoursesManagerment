/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.CourseSchedule;
import MODEL.Schedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DAOSchedule {
    Connection con=ConnectDB.connect();
    public List<Schedule> getAllSchedule(){
        List<Schedule> list=new ArrayList<>();
        try{
            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM dbo.Schedule ORDER BY Day ASC");
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                Date day=resultSet.getDate(2);
                String time=resultSet.getString(3);
                int numberOfShift=resultSet.getInt(4);
                int idCourseSchedule=resultSet.getInt(5);
                list.add(new Schedule(id, day, time, numberOfShift, idCourseSchedule));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public List<Schedule> getScheduleByIDCourseSchedule(int IdCourseSchedule){
        List<Schedule> list=new ArrayList<>();
        try{
            PreparedStatement statement=con.prepareStatement("SELECT * FROM dbo.Schedule WHERE IDCourseSchedule=? ORDER BY Day ASC");
            statement.setInt(1, IdCourseSchedule);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id=resultSet.getInt(1);
                Date day=resultSet.getDate(2);
                String time=resultSet.getString(3);
                int numberOfShift=resultSet.getInt(4);
                int idCourseSchedule=resultSet.getInt(5);
                list.add(new Schedule(id, day, time, numberOfShift, idCourseSchedule));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }
    public boolean checkSchedule(int idCourseSchedule, Date day) throws SQLException{
        String sql="SELECT * FROM dbo.Schedule WHERE IDCourseSchedule=? AND Day=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setInt(1, idCourseSchedule);
        statement.setDate(2, (java.sql.Date) day);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            int id=resultSet.getInt(1);
            System.out.println(""+id);
            if(id>0)
                return true;
        }
        return false;
    }
    public void insertSchedule(Date day, String time, int numberOfShift, int idCourseSchedule) throws SQLException{
        String sql="insert into dbo.Schedule(Day,Time,NumberOfShift,IDCourseSchedule) values(?,?,?,?)";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setDate(1, (java.sql.Date) day);
        statement.setString(2, time);
        statement.setInt(3, numberOfShift);
        statement.setInt(4, idCourseSchedule);
        statement.executeUpdate();
    }
    public void updateSchedule(int id, Date day, String time, int numberOfShift, int idCourseSchedule) throws SQLException{
        String sql="UPDATE dbo.Schedule SET IDCourse=?, Slot=?, SlotNow=?, StartDay=?, EndDay=? WHERE ID=?";
        PreparedStatement statement=con.prepareStatement(sql);
        
        statement.setInt(6, id);
        statement.executeUpdate();
    }
    public void deleteCourseSchedule(int id) throws SQLException{
        String sql="DELETE FROM dbo.Schedule WHERE ID=?";
        PreparedStatement statement=con.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}
