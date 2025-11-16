/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author thanh
 */
public class BorrowedDocuments implements Serializable {
    private int id;
    private double rentalPrice;
    private Documents document; 
    private ArrayList<ExistedFine> existedFine;
    public BorrowedDocuments() {
        
    }

    public BorrowedDocuments(int id, double rentalPrice, Documents document, ArrayList<ExistedFine> eF) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.document = document;
        this.existedFine = eF;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getRentalPrice() {
        return rentalPrice;
    }
    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
    public Documents getDocument() {
        return document;
    }
    public void setDocument(Documents document) {
        this.document = document;
    }
    public void setExistedFine(ArrayList<ExistedFine> ef){
        this.existedFine = ef;
    }
    public ArrayList<ExistedFine> getExistedFine(){ 
        return existedFine;
    }
}


