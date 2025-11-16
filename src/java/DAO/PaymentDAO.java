/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.*;
import java.sql.*;
import Model.Invoice;
import Model.Payment;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author thanh
 */
public class PaymentDAO extends DAO{
    public PaymentDAO(){
        super();
    }
    
    public boolean savePayment(Payment payment){
        boolean res = false;
        String sql = "INSERT INTO tblPayment ( date, type, note, invoice_id) VALUES (?, ?, ?, ?)";
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, sdf.format(payment.getDate()));
            ps.setString(2, payment.getType());
            ps.setString(3, payment.getNote());
            ps.setInt(4, payment.getInvoice().getId());
            
            int affectedRowsPayment = ps.executeUpdate();
            if(affectedRowsPayment == 0){
                throw new SQLException("Payment Fail");
            }
            else{
                res = true;
            }
        }catch(Exception e){
            e.printStackTrace(); 
        }
        return res;
    }
}
