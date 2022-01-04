package com.paymybuddy.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.application.service.ISecurityService;
import com.paymybuddy.application.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private ITransferService iTransferService;

    @Autowired
    private ISecurityService iSecurityService;

    @PostMapping(value="/transfer")
    public void addNewTransfer(@RequestParam("emailCorrespondence") String emailCorrespondence,
                               @RequestParam("amount") double amount) {
        String email = iSecurityService.getUserEmail();
        String description = "This is a new Transfer";
        iTransferService.sendMoneyAndUpdateUsersAccounts(email, emailCorrespondence, description, amount);
    }

    /*@GetMapping(value = "/transfer")
    public String getAllTransferMadeByUser(@RequestParam String email) throws JsonProcessingException {
        List<String> data = iTransferService.getAllTransferFromACorrespondence(email);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }*/

    @PutMapping(value="/sendingMoneyToBank/transfer")
    public void sendingMoneyToBank(@RequestParam String email,
                                   @RequestParam double amount) {
        iTransferService.sendMoneyToBankAccount(email, amount);
    }

    @PutMapping(value="/gettingMoneyFromBank/transfer")
    public void gettingMoneyFromBank(@RequestParam String email,
                                   @RequestParam double amount) {
        iTransferService.getMoneyFromBankAccount(email, amount);
    }
}
