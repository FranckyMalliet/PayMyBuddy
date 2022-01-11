package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    public User getUserDataFromEmail(String email) {
        User userData = userRepository.findUserByEmail(email);
        return userData;
    }

    public void updateUser(double amount, String email) {
        userRepository.updateUserAccount(amount, email);
    }
}
