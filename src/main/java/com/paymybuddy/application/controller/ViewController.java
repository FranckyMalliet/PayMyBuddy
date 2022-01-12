package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.model.Transfer;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.ISecurityService;
import com.paymybuddy.application.service.ITransferService;
import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    @Autowired
    private ITransferService iTransferService;

    @Autowired
    private ISecurityService iSecurityService;

    //LOGIN PAGE
    @GetMapping(value="/login")
    public String login(){
        return "login";
    }

    //ADD CONNECTION PAGE
    @GetMapping(value ="/connection")
    public String getNewConnectionPage(){
        return "addconnection";
    }

    // HOME PAGE
    @GetMapping(value="/correspondences")
    public String GetAListOfCorrespondencesFromAUser(Model model){
        List<Correspondence> correspondenceList = iCorrespondenceService.getAllCorrespondenceFromUser();
        model.addAttribute("correspondences", correspondenceList);
        return "home";
    }

    @GetMapping(value="/users")
    public String getNameFromUserCorrespondence(Model model){
        List<Correspondence> correspondenceList = iCorrespondenceService.getAllCorrespondenceFromUser();
        List<User> userList = new ArrayList<>();

        for(Correspondence correspondence : correspondenceList){
            userList.add(iUserService.getUserDataFromEmail(correspondence.getEmailCorrespondence()));
        }
        model.addAttribute("users", userList);
        return "home";
    }

    @GetMapping(value="/home")
    public String getValueTest(Model model){
        List<Transfer> transferList = iTransferService.getAllTransferOfAUser();
        List<Correspondence> correspondenceList = iCorrespondenceService.getAllCorrespondenceFromUser();

        model.addAttribute("correspondences", correspondenceList);
        model.addAttribute("transfers", transferList);
        return "home";
    }
}
