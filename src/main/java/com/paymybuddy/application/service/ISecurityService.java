package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;

public interface ISecurityService {

    public User getPrincipal();
    public String getUserEmail();
}
