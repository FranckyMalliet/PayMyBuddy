package com.paymybuddy.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class CorrespondenceController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    @Autowired
    private IUserService iUserService;

    @PostMapping(value="/correspondence")
    @RolesAllowed("USER")
    public void addNewCorrespondence(@RequestParam String email, @RequestParam String emailCorrespondence){
        iCorrespondenceService.createNewCorrespondence(email, emailCorrespondence);
    }

    @GetMapping(value = "/correspondence")
    @RolesAllowed("USER")
    public String getAllCorrespondenceOfAUser(@RequestParam String email) throws JsonProcessingException {
        List<Correspondence> data = iCorrespondenceService.getAllCorrespondenceFromUser(email);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }
}
