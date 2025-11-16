/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author thanh
 */
public class LoanSlip implements Serializable {
    private int id;
    private Date createDate;
    private LibraryStaff libraryStaff; 
    private Reader reader; 
    private ArrayList<BorrowedDocuments> borrowedDocuments;

    public LoanSlip() {
 
    }

    public LoanSlip(int id, Date createDate, LibraryStaff libraryStaff, Reader reader, ArrayList<BorrowedDocuments> borrowedDocuments) {
        this.id = id;
        this.createDate = createDate;
        this.libraryStaff = libraryStaff;
        this.reader = reader;
        this.borrowedDocuments = borrowedDocuments;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public LibraryStaff getLibraryStaff() {
        return libraryStaff;
    }
    public void setLibraryStaff(LibraryStaff libraryStaff) {
        this.libraryStaff = libraryStaff;
    }
    public Reader getReader() {
        return reader;
    }
    public void setReader(Reader reader) {
        this.reader = reader;
    }
    public ArrayList<BorrowedDocuments> getBorrowedDocuments(){
        return borrowedDocuments;
    }
    public void setBorrowedDocuments(ArrayList<BorrowedDocuments> bd){
        this.borrowedDocuments = bd;
    }
}