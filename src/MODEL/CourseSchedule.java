/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOCourseSchedule;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class CourseSchedule {
     int id;
     int idCourse;
     int slot;
     int slotNow;
     Date startDay;
        Date endDay;
     int idTeacher;
     
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSlotNow() {
        return slotNow;
    }

    public void setSlotNow(int slotNow) {
        this.slotNow = slotNow;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(int idTeacher) {
        this.idTeacher = idTeacher;
    }

    public CourseSchedule(int id, int idCourse, int slot, int slotNow, Date startDay, Date endDay, int idTeacher) {
        this.id = id;
        this.idCourse = idCourse;
        this.slot = slot;
        this.slotNow = slotNow;
        this.startDay = startDay;
        this.endDay = endDay;
        this.idTeacher = idTeacher;
    }
    
    public CourseSchedule() {
        this.id = 0;
        this.idCourse = 0;
        this.slot = 0;
        this.slotNow = 0;
        this.startDay = new Date(Date.UTC(2003, 7, 27, 0, 0, 0));
        this.endDay = new Date(Date.UTC(2003, 7, 27, 0, 0, 0));
        this.idTeacher = 0;
    }
    
    public ArrayList<CourseSchedule> getAllCouseSchedules()
    {
        DAO.DAOCourseSchedule db = new DAOCourseSchedule();
        return db.getAllDataCourseSchedules();
    }
    
    public ArrayList<CourseSchedule> getCourseFromTime(Date date)
    {
        DAO.DAOCourseSchedule db = new DAOCourseSchedule();
        ArrayList<CourseSchedule> data = new ArrayList<>();
        ArrayList<CourseSchedule> lst = db.getAllDataCourseSchedules();
            for(CourseSchedule cs : lst)
            {
               if(cs.getStartDay().before(date)&&cs.getEndDay().after(date))
               {
                   data.add(cs);
               }
            }
            return data;
    }
    
}
