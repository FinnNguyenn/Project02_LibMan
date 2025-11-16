/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.*;
import java.sql.*;
import Model.Invoice;
import Model.ExistedFine;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author thanh
 */
public class InvoiceDAO extends DAO{
    public InvoiceDAO(){
        super();
    }
    
    public boolean saveInvoice(Invoice invoice){
        boolean res = false;
        String sql = "INSERT INTO tblInvoice (createDate, staff_member_id) VALUES (?, ?)";
        String sql2 = "UPDATE tblBorrowedDocument SET invoice_id = ? WHERE id = ?";
        String sql3 = "INSERT INTO tblExistedFine (fee, fine_id, borrowedDocument_id) VALUES ( ?, ?, ?)";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sdf.format(invoice.getCreateDate()));
            ps.setInt(2, invoice.getLibraryStaff().getId());
            
            int affectedRowsInvoice = ps.executeUpdate();
            if(affectedRowsInvoice != 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    invoice.setId(rs.getInt(1));
                }
                else{
                    throw new SQLException("Fail Invoice id");
                }
                
                ps = con.prepareStatement(sql2);
                for(int i = 0; i < invoice.getBorrowedDocument().size();i++){
                    ps.setInt(1, invoice.getId());
                    ps.setInt(2, invoice.getBorrowedDocument().get(i).getId());
                    
                    int affectedRowsBorrowedDocuments = ps.executeUpdate();
                    if(affectedRowsBorrowedDocuments == 0){
                        throw new SQLException("BorrowedDocument Fail");
                    }  
                }
                
                
                ps = con.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                for(int i = 0; i < invoice.getBorrowedDocument().size(); i++){
                    if(invoice.getBorrowedDocument().get(i).getExistedFine() != null){
                        for(int j = 0; j < invoice.getBorrowedDocument().get(i).getExistedFine().size(); j++){
                        ps.setFloat(1, invoice.getBorrowedDocument().get(i).getExistedFine().get(j).getFee());
                        ps.setInt(2, invoice.getBorrowedDocument().get(i).getExistedFine().get(j).getFine().getId());
                        ps.setInt(3, invoice.getBorrowedDocument().get(i).getId());    
                        int affectedRowsExistedFine = ps.executeUpdate();
                        if(affectedRowsExistedFine == 0){
                            throw new SQLException("ExistedFine Fail");
                        }
                    }
                    }
                }
              
                res = true;
            }else{
                throw new SQLException("Invoice Fail");
                
            }
            
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return res;
    }
}
