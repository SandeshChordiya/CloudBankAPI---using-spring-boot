package com.example.CloudBankAPI.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bankapilog")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "from_username")
    private String from;
    @Column(name = "to_username")
    private String to;
    @Column(name = "transfer_money")
    private String transferMoney;
    @Column(name = "amount")
    private float amount;
    @Column(name = "description")
    private String description;

    public Log(){
    }

    public Log(int id, String date, String time, String from, String to, String transferMoney, float amount,
            String description) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.transferMoney = transferMoney;
        this.amount = amount;
        this.description = description;
    }

    public Log( String date, String time, String from, String to, String transferMoney, float amount,
            String description) {
        // this.id = id;
        this.date = date;
        this.time = time;
        this.from = from;
        this.to = to;
        this.transferMoney = transferMoney;
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(String transferMoney) {
        this.transferMoney = transferMoney;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
