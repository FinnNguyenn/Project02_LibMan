/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author thanh
 */
public class Reader extends Member implements Serializable {
    private int readerCode;
    private String studentCode;

    public Reader() {
        super();
    }

    public Reader(int id, String username, String password, String name, String address, Date birth, String email, String tel, int readerCode, String studentCode) {
        super(id, username, password, name, address, birth, email, tel);
        this.readerCode = readerCode;
        this.studentCode = studentCode;
    }

    public int getReaderCode() {
        return readerCode;
    }
    public void setReaderCode(int readerCode) {
        this.readerCode = readerCode;
    }
    public String getStudentCode() {
        return studentCode;
    }
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
}