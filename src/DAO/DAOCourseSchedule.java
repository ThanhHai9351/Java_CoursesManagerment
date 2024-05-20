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
    
}
