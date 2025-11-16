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
public class PaymentOfImporting implements Serializable {
    private int id;
    private Date date;
    private String type;
    private String note;
    private ImportRecord importRecord; // Quan hệ 1-1

    public PaymentOfImporting() {
        // Constructor mặc định
    }

    public PaymentOfImporting(int id, Date date, String type, String note, ImportRecord importRecord) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.note = note;
        this.importRecord = importRecord;
    }

    // Getters and Setters
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
    public ImportRecord getImportRecord() {
        return importRecord;
    }
    public void setImportRecord(ImportRecord importRecord) {
        this.importRecord = importRecord;
    }
}