/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOSchedule;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author DELL
 */
public class Schedule {
    int id;
    Date date;
    String time;
    int numerOfShift;
    int idCourseSchedule;
    DAOSchedule dao=new DAOSchedule();
    public Schedule() {
    }

    public Schedule(int id, Date date, String time, int numerOfShift, int idCourseSchedule) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.numerOfShift = numerOfShift;
        this.idCourseSchedule = idCourseSchedule;
    }
    public List<Schedule> getAllSchedule(){
        return dao.getAllSchedule();
    }
    public List<Schedule> getScheduleByIDCourseSchedule(int IdCourseSchedule){
        return dao.getScheduleByIDCourseSchedule(IdCourseSchedule);
    }
    public void insertSchedule(Date day, String time, int numberOfShift, int idCourseSchedule){
        try {
            dao.insertSchedule(day, time, numberOfShift, idCourseSchedule);
        } catch (SQLException ex) {
            Logger.getLogger(Schedule.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateSchedule(int id, Date day, String time, int numberOfShift, int idCourseSchedule) throws SQLException{
        dao.updateSchedule(id, day, time, numberOfShift, idCourseSchedule);
    }
    public void deleteSchedule(int id) throws SQLException{
        dao.deleteCourseSchedule(id);
    }
    public boolean checkSchedule(int idCourseSchedule, Date day) throws SQLException{
        return dao.checkSchedule(idCourseSchedule, day);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getNumerOfShift() {
        return numerOfShift;
    }

    public void setNumerOfShift(int numerOfShift) {
        this.numerOfShift = numerOfShift;
    }

    public int getIdCourseSchedule() {
        return idCourseSchedule;
    }

    public void setIdCourseSchedule(int idCourseSchedule) {
        this.idCourseSchedule = idCourseSchedule;
    }
    
}
