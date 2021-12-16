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

    //DELETE
    public void deleteUser(String email) {
        userRepository.deleteUser(email);
    }



    //Password Encoder
    private String passwordEncoder(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = password;
        String encodedPassword = encoder.encode(rawPassword);
        return encodedPassword;
    }





    //LOGIN
    public void userLogin(String email, String password){
        checkUserEmail(email);
        checkUserPassword(email, password);
    }

    public void checkUserEmail(String email){
        String searchEmail = userRepository.findUserEmailFromUser(email);
        if (searchEmail == null){
            System.out.println(email + " doesn't exist");
        } else {
            System.out.println("You are currently logged with the email : " + email);
        }
    }

    public void checkUserPassword(String email, String password){
        String searchPassword = userRepository.findUserPassword(email);
        if(!searchPassword.equals(password)){
            System.out.println("Incorrect password, try again !");
        } else {
            System.out.println("Welcome !");
        }
    }
}
