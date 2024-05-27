/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOReceipt;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Receipt {
    String name,nameCourse;
    float price;
    Date startDay;
    DAOReceipt dao=new DAOReceipt();
    public List<Receipt> getAllReceipt(){
        return dao.getAllReceipt();
    }
    public Receipt() {
    }

    public Receipt(String name, String nameCourse, float price, Date startDay) {
        this.name = name;
        this.nameCourse = nameCourse;
        this.price = price;
        this.startDay = startDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }
    
}
