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
public class Payment implements Serializable {
    private int id;
    private Date date;
    private String type;
    private String note;
    private Float amount;
    private Invoice invoice; 

    public Payment() {
       
    }

    public Payment(int id, Date date, String type, String note, Float amount, Invoice invoice) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.note = note;
        this.amount = amount;
        this.invoice = invoice;
    }

  
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public Invoice getInvoice() {
        return invoice;
    }
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
