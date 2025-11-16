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
public class ExistedFine implements Serializable {
    private int id;
    private Float fee;
    private Fine fine; 

    public ExistedFine() {
        
    }

    public ExistedFine(int id, Float fee, Fine fine) {
        this.id = id;
        this.fee = fee;
        this.fine = fine;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Float getFee() {
        return fee;
    }
    public void setFee(Float fee) {
        this.fee = fee;
    }
    public Fine getFine() {
        return fine;
    }
    public void setFine(Fine fine) {
        this.fine = fine;
    }
}