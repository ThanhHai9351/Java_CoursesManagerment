/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ConnectDB.connect;
import MODEL.Course;
import MODEL.CourseSchedule;
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
public class DAOCourseSchedule {
    Connection connection = connect();
    
    public ArrayList<CourseSchedule> getAllDataCourseSchedules() {
        ArrayList<CourseSchedule> data = new ArrayList<>();

        try  {
            String sql = "SELECT * FROM CourseSchedule";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                int idcourse = resultSet.getInt("IDCourse");
                int slot = resultSet.getInt("Slot");
                int slotnow = resultSet.getInt("SlotNow");
                 Date startdate = resultSet.getDate("StartDay");
                  Date enddate = resultSet.getDate("EndDay");
                  int idteacher = resultSet.getInt("IDTeacher");
               
                CourseSchedule item = new CourseSchedule(id, idcourse,slot,slotnow,startdate,enddate,idteacher);
                
                data.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public boolean insertCourseSchedule(int idCourse,int slot,int slotNow, java.sql.Date startDate,java.sql.Date endDate,int idTeacher) throws SQLException{
        try {
            java.sql.Date sqlDateStart = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(endDate.getTime());
            String sql = "insert into CourseSchedule(IDCourse,Slot,SlotNow,StartDay,EndDay,IDTeacher) values (?,?,?,?,?,?) ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idCourse);
            statement.setInt(2, slot);
            statement.setInt(3, slotNow);
            statement.setDate(4, sqlDateStart);
            statement.setDate(5, sqlDateEnd);
            statement.setInt(6, idTeacher);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void updateCourseSchedule(int id, int idCourse,int slot,int slotNow, java.sql.Date startDate,java.sql.Date endDate, int idTeacher) throws SQLException{
        String sql="UPDATE dbo.CourseSchedule SET IDCourse=?, Slot=?, SlotNow=?, StartDay=?, EndDay=?, IDTeacher=? WHERE ID=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1, idCourse);
        statement.setInt(2, slot);
        statement.setInt(3, slotNow);
        statement.setDate(4, startDate);
        statement.setDate(5, endDate);
        statement.setInt(6,idTeacher);
        statement.setInt(7, id);
        statement.executeUpdate();
    }
    public void deleteCourseSchedule(int id) throws SQLException{
        String sql="DELETE FROM dbo.CourseSchedule WHERE ID=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
    public int getIDCourseSchedule(int idCourse, java.sql.Date startDay, int idTeacher) throws SQLException{
        String sql="SELECT TOP 1 ID \n" +
                    "FROM dbo.CourseSchedule\n" +
                    "WHERE IDCourse = ? AND StartDay = ? AND IDTeacher = ?\n" +
                    "ORDER BY ID DESC";
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setInt(1, idCourse);
        statement.setDate(2, startDay);
        statement.setInt(3, idTeacher);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        int id=resultSet.getInt(1);
        if(id>1)
            return id;
        return -1;
    }
}
