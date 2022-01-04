package com.paymybuddy.application.service;

import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    //CRUD ENDPOINTS
    //CREATE
    public void addNewUser(User user) {
        String email = user.getEmail();

        if(Objects.isNull(getUserDataFromEmail(email))){
            String encodedPassword = passwordEncoder(user.getPassword());
            user.setPassword(encodedPassword);

            userRepository.save(user);
        } else {
            System.out.println("This email : " + email + " already exists in the database");
        }
    }

    //READ
    public User getUserDataFromEmail(String email){
        User userData = userRepository.findUserByEmail(email);
        return userData;
    }

    public void getPasswordFromUser(String email){
        userRepository.findUserPassword(email);
    }

    //UPDATE
    public void updateUser(double amount, String email){
        userRepository.updateUserAccount(amount, email);
    }



    //Password Encoder
    public String passwordEncoder(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = password;
        String encodedPassword = encoder.encode(rawPassword);
        return encodedPassword;
    }

    public String passwordCreator(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = password;
        String encodedPassword = encoder.encode(rawPassword);
        return encodedPassword;
    }
}
