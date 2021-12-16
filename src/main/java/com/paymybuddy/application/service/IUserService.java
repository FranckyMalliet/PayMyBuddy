package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

public interface IUserService {

    //CRUD ENDPOINTS
    //CREATE
    public void addNewUser(User user);

    //READ
    public User getUserDataFromEmail(String email);

    //UPDATE
    public void updateUser(double amount, String email);

    //DELETE
    public void deleteUser(String email);

    //SECURITY
    public void userLogin(String email, String password);
    public void getPasswordFromUser(String email);

    public String passwordCreator(String password);
}
