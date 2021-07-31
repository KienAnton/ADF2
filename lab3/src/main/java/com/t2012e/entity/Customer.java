package com.t2012e.entity;


import com.t2012e.reflection.myannotation.Id;
import com.t2012e.reflection.myannotation.Table;

@Table(name = "khachhang")
public class Customer {
    @Id(autoIncrement = false)
    private String identityNumber;
    private double balance;
    private String name;
    private String email;

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
