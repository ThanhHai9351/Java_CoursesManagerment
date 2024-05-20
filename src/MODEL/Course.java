/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOCourse;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class Course {
    int id;
    String name;
    float price;

    public Course(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
     public Course() {
        this.id = 0;
        this.name = "";
        this.price = 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
    public ArrayList<Course> getAllCourses()
    {
        DAOCourse db = new DAOCourse();
        return db.getAllDataCourses();
    }
    
    public String getNameCourseFromID(int id)
    {
        DAOCourse db = new DAOCourse();
        ArrayList<Course> lstcs =  db.getAllDataCourses();
        for(Course c : lstcs)
        {
            if(c.getId() == id)
                return c.getName().trim();
        }
        return null;
    }
    
    public int getCountCouses()
    {
        int count = 0;
        DAOCourse db = new DAOCourse();
        ArrayList<Course> lstcs =  db.getAllDataCourses();
        for(Course c : lstcs)
        {
            count ++;
        }
        return count;
    }
    
}
