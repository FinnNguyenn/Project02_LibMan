/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.*;
import java.sql.*;
import Model.Fine;

/**
 *
 * @author thanh
 */
public class FineDAO extends DAO{
    public FineDAO(){
        super();
    }
    
    public ArrayList<Fine> getListFine(){
        String sql = "SELECT * FROM tblFine";
        ArrayList<Fine> kq = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Fine tmp = new Fine();
                tmp.setId(rs.getInt("id"));
                tmp.setName(rs.getString("name"));
                tmp.setFee(rs.getFloat("fee"));
                kq.add(tmp);
            }
            
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return kq;
    }
}
