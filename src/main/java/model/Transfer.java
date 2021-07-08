package model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transfers")
public class Transfer {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    protected int id;

    @Column(name = "to_account")
    private String to_account;
    
    @Column(name = "from_account")
    private String from_account;
    
    @Column(name = "amount")
    private double amount;
    
    @Column(name = "created_at")
    private String created_at;
    
    public Transfer() {
    }
 
    public Transfer(String to_account,String from_account, double amount,String created_at) {
    super();
    this.to_account =to_account;
    this.from_account = from_account;
    this.amount = amount;
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
    public double getAmount() {
        return amount;
    }
     public void setAmount(double amount) {
        this.amount=amount;
    }
    public void setToAcc(String to_acc) {
        this.to_account =to_acc;
    }
     public String getToAcc() {
        return to_account;
    }
     public void setFromAcc(String from_acc) {
        this.from_account =from_acc;
    }
     public String getFromAcc() {
        return from_account;
    }
   
}