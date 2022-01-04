package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

public interface IUserService {

    //CRUD ENDPOINTS
    //CREATE
    public void addNewUser(User user);

    //READ
    public User getUserDataFromEmail(String email);
    public void getPasswordFromUser(String email);

    //UPDATE
    public void updateUser(double amount, String email);

    //SECURITY
    public String passwordEncoder(String password);
    public String passwordCreator(String password);
}
