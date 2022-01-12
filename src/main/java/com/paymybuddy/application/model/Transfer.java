package com.paymybuddy.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_transfer")
    private int idTransfer;

    @Column(name="description")
    private String description;

    @Column(name="amount")
    private double amount;

    @Column(name="transaction_fee")
    private double transactionFee;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name="idCorrespondence")
    @JsonBackReference
    private Correspondence correspondence;

    public Correspondence getCorrespondence() {
        return correspondence;
    }

    public void setCorrespondence(Correspondence correspondence) {
        this.correspondence = correspondence;
    }

    public int getIdTransfer() {
        return idTransfer;
    }

    public void setIdTransfer(int idTransfer) {
        this.idTransfer = idTransfer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(double transactionFee) {
        this.transactionFee = transactionFee;
    }
}
