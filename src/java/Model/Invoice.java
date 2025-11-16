/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.*;
import java.io.Serializable;


/**
 *
 * @author thanh
 */
public class Invoice implements Serializable {
    private int id;
    private Date createDate;
    private Float amount;
    private ArrayList<BorrowedDocuments> borrowedDocument; 
    private Staff libraryStaff; 

    public Invoice() {

    }

    public Invoice(int id, Date createDate, Float amount , ArrayList<BorrowedDocuments>  borrowedDocument, Staff libraryStaff) {
        this.id = id;
        this.createDate = createDate;
        this.amount = amount;
        this.borrowedDocument = borrowedDocument;
        this.libraryStaff = libraryStaff;
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
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public ArrayList<BorrowedDocuments> getBorrowedDocument() {
        return borrowedDocument;
    }
    public void setBorrowedDocument(ArrayList<BorrowedDocuments> borrowedDocument) {
        this.borrowedDocument = borrowedDocument;
    }
    public Staff getLibraryStaff() {
        return libraryStaff;
    }
    public void setLibraryStaff(Staff libraryStaff) {
        this.libraryStaff = libraryStaff;
    }
}
