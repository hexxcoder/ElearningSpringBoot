package com.example.smartcontactmanager.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TransactionID")
    private Long transactionID;

    @ManyToOne
    @JoinColumn(name = "email")
    private User email;

    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;

    @Column(name = "Transaction_Date")
    private Date transactionDate;

    @Column(name = "Amount")
    private double amount;

    public Transactions(){
        super();
    }

    public Long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(Long transactionID) {
        this.transactionID = transactionID;
    }

    public User getEmail() {
        return email;
    }

    public void setEmail(User email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transactions [transactionID=" + transactionID + ", email=" + email + ", course=" + course
                + ", transactionDate=" + transactionDate + ", amount=" + amount + "]";
    }

    // Getters and setters
}

