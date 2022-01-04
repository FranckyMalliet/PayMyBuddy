package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Transfer;

import java.util.List;

public interface ITransferService {

    //CRUD ENDPOINTS
    //CREATE
    public void sendMoneyAndUpdateUsersAccounts(String email, String emailCorrespondence,  String description, double amount);

    //UPDATE
    public void sendMoneyToBankAccount(String email, double amount);
    public void getMoneyFromBankAccount(String email, double amount);

    //VIEW
    public List<String> getAllTransferFromACorrespondence(String email);
    public List<Transfer> getAllTransferOfAUser();

    public List<Transfer> getAllTransfer();
}
