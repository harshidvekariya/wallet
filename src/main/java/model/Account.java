/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    protected int id;

    @Column(name = "accnumber")
    private String accnumber;
    
    @Column(name = "balance")
    private double balance;
    
    @Column(name = "AccName")
    private String AccName;
    
    @Column(name = "currency")
    private String currency;
    
    @Column(name = "created_at")
    private String created_at;
    
    public Account() {
    }
 
    public Account(String accno, double balance,String accname,String currency,String created_at) {
    super();
    this.accnumber = accno;
    this.currency = currency;
    this.AccName = accname;
    this.balance=balance;
    this.created_at=created_at;
    }

    public Account(int id,String accno, double balance,String accname,String currency,String created_at) {
    super();
    this.id = id;
    this.accnumber = accno;
    this.currency = currency;
    this.AccName = accname;
    this.balance=balance;
    this.created_at=created_at;
    }
    public int getId() {
    return id;
    }
    public void setId(int id) {
    this.id = id;
    }
    public String getDate() {
        return created_at;
    }
    public void setDate(String created_at) {
        this.created_at = created_at;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance =balance;
    }
     public String getAccNo() {
        return accnumber;
    }
    public void setAccNo(String accnumber) {
        this.accnumber= accnumber;
    }
     public String getAccName() {
        return AccName;
    }
    public void setAccName(String AccName) {
        this.AccName= AccName;
    }
     public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency){
        this.currency= currency;
    }
}