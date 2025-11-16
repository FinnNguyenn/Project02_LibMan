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
public class ImportRecord implements Serializable {
    private int id;
    private Date createDate;
    private int numberOfDocuments;
    private double totalAmount;
    private Documents document; // Quan hệ 1-n
    private LibraryStaff libraryStaff; // Quan hệ 1-n

    public ImportRecord() {
        // Constructor mặc định
    }

    public ImportRecord(int id, Date createDate, int numberOfDocuments, double totalAmount, Documents document, LibraryStaff libraryStaff) {
        this.id = id;
        this.createDate = createDate;
        this.numberOfDocuments = numberOfDocuments;
        this.totalAmount = totalAmount;
        this.document = document;
        this.libraryStaff = libraryStaff;
    }

    // Getters and Setters
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
    public int getNumberOfDocuments() {
        return numberOfDocuments;
    }
    public void setNumberOfDocuments(int numberOfDocuments) {
        this.numberOfDocuments = numberOfDocuments;
    }
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Documents getDocument() {
        return document;
    }
    public void setDocument(Documents document) {
        this.document = document;
    }
    public LibraryStaff getLibraryStaff() {
        return libraryStaff;
    }
    public void setLibraryStaff(LibraryStaff libraryStaff) {
        this.libraryStaff = libraryStaff;
    }
}
