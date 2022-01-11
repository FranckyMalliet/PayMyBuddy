package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transfer;

import java.util.List;

public interface ITransferService {

    //CRUD ENDPOINTS
    //CREATE
    public void sendMoneyAndUpdateUsersAccounts(String email, String emailCorrespondence,  String description, double amount);

    //UPDATE
    public void sendMoneyToBankAccount(double amount);
    public void getMoneyFromBankAccount(double amount);

    //VIEW
    public List<Transfer> getAllTransferOfAUser();
}
