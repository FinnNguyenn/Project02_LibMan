/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Model.Staff;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author thanh
 */
public class StaffDAO extends DAO{
    public StaffDAO(){
        super();
    }
    
    public boolean checkLogin(Staff staff){
        boolean res = false;
        String sql = "SELECT * FROM tblStaff INNER JOIN tblMember ON tblStaff.member_id = tblMember.id WHERE username = ? and password = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, staff.getUsername());
            ps.setString(2, staff.getPassword());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                staff.setRole(rs.getString("role"));
                staff.setId(rs.getInt("id"));
                staff.setName(rs.getString("name"));
            }
            if(staff.getRole() != null && staff.getRole().equals("Library Staff")){
                res = true;
            }
        }catch(Exception e){
            e.printStackTrace(); 
        }
        
        return res;
    }
}
