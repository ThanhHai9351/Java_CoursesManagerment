/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOAccount;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Account {
    int id,role;
    String name,address,idcard,phone,pass;
    Date dob;
    boolean gender;

    public Account(int id, String name, Date dob, String phone, String idcard, String address, boolean gender, String pass, int role) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.address = address;
        this.idcard = idcard;
        this.phone = phone;
        this.pass = pass;
        this.dob = dob;
        this.gender = gender;
    }

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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

    

    
    public Account() {
        this.id = 0;
        this.role = 0;
        this.name = "";
        this.address = "";
        this.idcard = "";
        this.phone = "";
        this.dob = new Date(Date.UTC(2003, 7, 27, 0, 0, 0));
        this.gender = true;
        this.pass = "";
    }
    
    public ArrayList<Account> getAllAccount()
    {
        DAOAccount db = new DAOAccount();
        return db.getAllDataAccount();
    }
    
    public ArrayList<Account> getAllStudent()
    {
        DAOAccount db = new DAOAccount();
        ArrayList<Account> newLst = new ArrayList<>();
        ArrayList<Account> acs = db.getAllDataAccount();
        for(Account a : acs)
        {
            if(a.getRole() == 2)
                newLst.add(a);
        }
        return newLst;
    }
    
    public int getCountStudent()
    {
        int count = 0;
        DAOAccount db = new DAOAccount();
        ArrayList<Account> acs = db.getAllDataAccount();
        for(Account a : acs)
        {
            if(a.getRole() == 2)
            {
                count++;
            }
        }
        return count;
    }
    
     public int getCountTeacher()
    {
        int count = 0;
        DAOAccount db = new DAOAccount();
        ArrayList<Account> acs = db.getAllDataAccount();
        for(Account a : acs)
        {
            if(a.getRole() == 1)
            {
                count++;
            }
        }
        return count;
    }
     
    public String getNameAccountFromID(int id)
    {
        DAOAccount db = new DAOAccount();
        ArrayList<Account> acs = db.getAllDataAccount();
        for(Account a : acs)
        {
            if(a.getId() == id)
                return a.getName();
        }
        return null;
    }
    
}
