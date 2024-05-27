/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODEL;

import DAO.DAOMail;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public class Mail {
    int id,idAccount;
    String name,title, message;
    boolean isRead;
    DAOMail dao=new DAOMail();
    public Mail() {
    }

    public Mail(int id, int idAccount, String name, String title, String message, boolean isRead) {
        this.id = id;
        this.idAccount = idAccount;
        this.name = name;
        this.title = title;
        this.message = message;
        this.isRead = isRead;
    }
    public List<Mail> getAllMail(){
        return dao.getAllMail();
    }
    public void updateMail(int id) throws SQLException{
        dao.upDateRead(id);
    }
    public void deleteMail(int id){
        try{
            dao.delete(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
    
}
