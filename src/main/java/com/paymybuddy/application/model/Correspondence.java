package com.paymybuddy.application.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="correspondence")
public class Correspondence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_correspondence")
    private Integer idCorrespondence;

    @Column(name="user_email_correspondence")
    private String emailCorrespondence;

    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinColumn(name="email")
    @JsonBackReference
    private User user;

    @OneToMany(
            mappedBy = "correspondence",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    List<Transfer> transferList = new ArrayList<>();

    public void addTransfer(Transfer transfer){
        transferList.add(transfer);
        transfer.setCorrespondence(this);
    }

    public void removeTransfer(Transfer transfer){
        transferList.remove(transfer);
        transfer.setCorrespondence(this);
    }

    public Integer getIdCorrespondence() {
        return idCorrespondence;
    }

    public void setIdCorrespondence(Integer idCorrespondence) {
        this.idCorrespondence = idCorrespondence;
    }

    public String getEmailCorrespondence() {
        return emailCorrespondence;
    }

    public void setEmailCorrespondence(String emailCorrespondence) {
        this.emailCorrespondence = emailCorrespondence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transfer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Transfer> transferList) {
        this.transferList = transferList;
    }
}
