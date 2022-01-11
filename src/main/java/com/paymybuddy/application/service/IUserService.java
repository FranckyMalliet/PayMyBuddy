package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

public interface IUserService {

    //CRUD ENDPOINTS
    //READ
    public User getUserDataFromEmail(String email);

    //UPDATE
    public void updateUser(double amount, String email);
}
