/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author thanh
 */
public class Documents implements Serializable {
    private int documentCode;
    private int id;
    private String title;
    private String author;
    private String publisher;
    private double rentalPrice;
    private double importedPrice;
    private Supplier supplier; 

    public Documents() {
        
    }

    public Documents(int documentCode, int id, String title, String author, String publisher, double rentalPrice, double importedPrice, Supplier supplier) {
        this.documentCode = documentCode;
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.rentalPrice = rentalPrice;
        this.importedPrice = importedPrice;
        this.supplier = supplier;
    }

   
    public int getDocumentCode() {
        return documentCode;
    }
    public void setDocumentCode(int documentCode) {
        this.documentCode = documentCode;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public double getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public double getImportedPrice() {
        return importedPrice;
    }
    public void setImportedPrice(double importedPrice) {
        this.importedPrice = importedPrice;
    }
    public Supplier getSupplier() {
        return supplier;
    }
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}