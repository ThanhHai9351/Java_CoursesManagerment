/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;


public class ConnectDB {
    Connection con;

    public ConnectDB() {
    }

    public ConnectDB(Connection con) {
        this.con = con;
    }
    
    public static Connection connect(){
        Connection conn=null;
        try{
            String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(driver);
              String url="jdbc:sqlserver://THANHHAI:1433;databaseName=CoursesManagerment;integratedSecurity = true;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8";
            conn=DriverManager.getConnection(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
