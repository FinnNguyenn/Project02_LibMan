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
import Model.Reader;
import Model.ReaderCard;
import java.text.SimpleDateFormat;

/**=+++
 *
 * @author thanh
 */
public class ReaderDAO extends DAO{
    public ReaderDAO(){
        super();
    }
    
    public Reader findReader(int readerCode){
        Reader reader = new Reader();
        String sql = "SELECT * FROM tblReader INNER JOIN tblMember ON tblReader.member_id = tblMember.id WHERE readerCode = ?";
        
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, readerCode);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                reader.setName(rs.getString("name"));
                reader.setReaderCode(rs.getInt("readerCode"));
                reader.setAddress(rs.getString("address"));
                reader.setEmail(rs.getString("email"));
            }
        }
        catch(Exception e){
            e.printStackTrace(); 
        }
        return reader;
    }
    
    
}
