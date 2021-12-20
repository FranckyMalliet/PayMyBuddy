package com.paymybuddy.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
public class UserController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/userTest")
    @RolesAllowed({"USER", "ADMIN"})
    public String user(){
        return "Ca marche Utilisateur !";
    }

    @RequestMapping("/adminTest")
    @RolesAllowed("ADMIN")
    public String admin(){
        return "Ca marche Admin !";
    }

    @PostMapping(value = "/user")
    public void addNewUser(@RequestBody User user){
        iUserService.addNewUser(user);
    }

    @GetMapping(value = "/user")
    public String getUserData(@RequestParam String email) throws JsonProcessingException {
        User userData = iUserService.getUserDataFromEmail(email);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userData);
    }

    @DeleteMapping(value = "/user")
    public void deleteUser(@RequestParam String email){
        iUserService.deleteUser(email);
    }


    /*@GetMapping(value="/login")
    public void Login (@RequestParam String email, @RequestParam String password){
        iUserService.userLogin(email, password);
    }*/






}
