package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SecurityService implements ISecurityService{

    public User getPrincipal(){
        User user = null;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof User){
            user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return user;
    }

    public String getUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
