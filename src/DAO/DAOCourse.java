/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.ConnectDB.connect;
import MODEL.Course;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class DAOCourse {
    Connection connection = connect();
    
    public ArrayList<Course> getAllDataCourses() {
        ArrayList<Course> data = new ArrayList<>();

        try  {
            String sql = "SELECT * FROM Course";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                Float price = resultSet.getFloat("Price");

                Course item = new Course(id, name.trim(), price);
                data.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
  
}
