/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOTeacher;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Teacher {
    int id;
    String name;
    Date dob;
    boolean gender;
    String address;
    String idCard;
    String phone;
    int role;
    String password;
    DAOTeacher daot=new DAOTeacher();

    public Teacher() {
    }

    public Teacher(int id, String name, Date dob, boolean gender, String address, String idCard, String phone, int role, String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.idCard = idCard;
        this.phone = phone;
        this.role = role;
        this.password = password;
    }
    public List<Teacher> getAllTeacher() throws SQLException{
        return daot.getAllTeacher();
    }
    public void insertTeacher(String name,java.sql.Date dob,boolean gender, String address, String idCard,String phone) throws SQLException{
        daot.insertTeacher(name, dob, gender, address, idCard, phone);
    }
    public void updateTeacher(String name,java.sql.Date dob,boolean gender, String address, String idCard,String phone) throws SQLException{
        daot.updateTeacher(name, dob, gender, address, idCard, phone);
    }
    public void deleteTeacher(String IdCard) throws SQLException{
        daot.deleteTeacher(IdCard);
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
