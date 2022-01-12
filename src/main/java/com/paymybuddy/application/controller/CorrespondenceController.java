package com.paymybuddy.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.ISecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CorrespondenceController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    @Autowired
    private ISecurityService iSecurityService;

    @PostMapping(value="/correspondence")
    public void addNewCorrespondence(@RequestParam String emailCorrespondence){
        String email = iSecurityService.getUserEmail();
        iCorrespondenceService.createNewCorrespondence(email, emailCorrespondence);
    }
}
