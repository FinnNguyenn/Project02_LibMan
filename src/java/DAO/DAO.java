/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author thanh
 */

public class DAO {
    public static Connection con;

    public DAO() {
        if(con == null){
            String dbUrl = "jdbc:mysql://localhost:3306/LibManReal?autoReconnect=true&useSSL=false";
            String dbClass = "com.mysql.cj.jdbc.Driver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "root", "Anlatoi123");
                System.out.println("Kết nối CSDL thành công");
            } catch (Exception e) {
                System.out.println("Kết nối CSDL thất bại");
                e.printStackTrace();
            }
        }
    }
}