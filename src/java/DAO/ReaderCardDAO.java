/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import static DAO.DAO.con;
import Model.ReaderCard;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author thanh
 */
public class ReaderCardDAO extends DAO{
    public ReaderCardDAO(){
        super();
    }
    
public boolean addNewReader(ReaderCard reader) throws SQLException {
    boolean check = false;
    String sql1 = "INSERT INTO tblMember (username, password, name, address, birth, email, tel) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String sql2 = "INSERT INTO tblReader (studentCode, member_id) VALUES (?,?)";
    String sql3 = "INSERT INTO tblReaderCard(reader_code, createDate, expirationDate) VALUES (?, ?, ?)";

    int memberId = 0;
    int readerCode = 0;

    try {
        con.setAutoCommit(false); 
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement ps1 = con.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS)) {
            ps1.setString(1, reader.getUsername());
            ps1.setString(2, reader.getPassword());
            ps1.setString(3, reader.getName());
            ps1.setString(4, reader.getAddress());
            ps1.setString(5, sdf.format(reader.getBirth()));
            ps1.setString(6, reader.getEmail());
            ps1.setString(7, reader.getTel());
            
            if (ps1.executeUpdate() == 0) {
                con.rollback();
                return false; 
            }

            try (ResultSet rs1 = ps1.getGeneratedKeys()) {
                if (rs1.next()) {
                    memberId = rs1.getInt(1);
                } else {
                    con.rollback();
                    return false;
                }
            }
        }

        try (PreparedStatement ps2 = con.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS)) {
            ps2.setString(1, reader.getStudentCode());
            ps2.setInt(2, memberId);
            
            if (ps2.executeUpdate() == 0) {
                con.rollback(); 
                return false;
            }

            try (ResultSet rs2 = ps2.getGeneratedKeys()) {
                if (rs2.next()) {
                    readerCode = rs2.getInt(1); 
                } else {
                    con.rollback();
                    return false;
                }
            }
        }
        
        try (PreparedStatement ps3 = con.prepareStatement(sql3)) {
            ps3.setInt(1, readerCode);
            ps3.setString(2, sdf.format(reader.getCreateDate()));
            ps3.setString(3, sdf.format(reader.getExpirationDate()));
            
            if (ps3.executeUpdate() == 0) {
                con.rollback(); 
                return false;
            } else {
                con.commit();
                check = true;
            }
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        try {
            if (con != null) {
                System.err.println("Transaction failed due to exception. Rolling back changes.");
                con.rollback(); 
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        
    } finally {
        try {
            if (con != null) {
                con.setAutoCommit(true);
            }
        } catch (SQLException autoCommitEx) {
            autoCommitEx.printStackTrace();
        }
    }
    
    return check;
}
}
