package com.paymybuddy.application.controller;

import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.model.Transfer;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.ISecurityService;
import com.paymybuddy.application.service.ITransferService;
import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    /**
     * This URI get all correspondences from a user email,
     * the user email is given by the principal object from
     * spring security service
     * @param model
     * @return all correspondences from a user on the home page
     */

    @GetMapping(value="/correspondences")
    public String GetAListOfCorrespondencesFromAUser(Model model){
        List<Correspondence> correspondenceList = iCorrespondenceService.getAllCorrespondenceFromUser();
        model.addAttribute("correspondences", correspondenceList);
        return "home";
    }

    /**
     * This URI get the  from a user email,
     * the user email is given by the principal object from
     * spring security service
     * @param model
     * @return all correspondences from a user on the home page
     */

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
