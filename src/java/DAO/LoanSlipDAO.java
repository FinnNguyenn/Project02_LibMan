/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.LoanSlip;
import Model.BorrowedDocuments;
import Model.Documents;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author thanh
 */
public class LoanSlipDAO extends DAO {
    public LoanSlipDAO() {
        super();
    }

    public ArrayList<LoanSlip> getBorrowedDocuments(int readerCode) {
        
        Map<Integer, LoanSlip> loanSlipMap = new LinkedHashMap<>();
        
  
        String sql = "SELECT " +
                     "    ls.id AS loanSlip_id, ls.createDate, " +
                     "    bd.id AS borrowedDoc_id, bd.rentalPrice, " +
                     "    d.documentCode, d.title, d.author " +
                     "FROM " +
                     "    tblLoanSlip AS ls " +
                     "JOIN " +
                     "    tblBorrowedDocument AS bd ON ls.id = bd.loan_slip_id " +
                     "JOIN " +
                     "    tblDocument AS d ON bd.document_code = d.documentCode " +
                     "WHERE " +
                     "    ls.reader_code = ? " +
                     "ORDER BY " +
                     "    ls.id";
        
        String sql2 = "SELECT invoice_id FROM tblBorrowedDocument WHERE id = ?";
        ArrayList<LoanSlip> kq = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, readerCode);
            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int loanSlipId = rs.getInt("loanSlip_id");
                    LoanSlip loanSlip = loanSlipMap.get(loanSlipId);
                    if (loanSlip == null) {
                        loanSlip = new LoanSlip();
                        loanSlip.setId(loanSlipId);
                        loanSlip.setCreateDate(rs.getDate("createDate"));
                        loanSlip.setBorrowedDocuments(new ArrayList<>()); 
                        
                        loanSlipMap.put(loanSlipId, loanSlip);
                    }

                    Documents doc = new Documents();
                    doc.setDocumentCode(rs.getInt("documentCode"));
                    doc.setTitle(rs.getString("title"));
                    doc.setAuthor(rs.getString("author"));
                    
                    BorrowedDocuments borrowedDoc = new BorrowedDocuments();
                    borrowedDoc.setId(rs.getInt("borrowedDoc_id"));
                    borrowedDoc.setRentalPrice(rs.getFloat("rentalPrice"));
                    borrowedDoc.setDocument(doc); 
                    loanSlip.getBorrowedDocuments().add(borrowedDoc);
                }
            ps = con.prepareStatement(sql2);
            kq = new ArrayList<>(loanSlipMap.values());
           
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        
        return kq;
    }
    
    public boolean checkStatus(int code){
        boolean res = false;
        String sql = "SELECT invoice_id FROM tblBorrowedDocument WHERE id = ?";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                rs.getObject("invoice_id");
                if(rs.wasNull()){
                        res = true;  
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return res;
    }
}