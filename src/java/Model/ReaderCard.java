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
public class ReaderCard extends Reader implements Serializable {
    private int id;
    private Date createDate;
    private Date expirationDate;

    public ReaderCard() {
        
    }

    public ReaderCard(int id, Date createDate, Date expirationDate) {
        this.id = id;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
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
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}