package com.paymybuddy.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.application.service.ICorrespondenceService;
import com.paymybuddy.application.service.ITransferService;
import com.paymybuddy.application.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class TransferController {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    @Autowired
    private ITransferService iTransferService;

    @PostMapping(value="/transfer")
    public void addNewTransfer(@RequestParam String email,
                               @RequestParam("emailCorrespondence") String emailCorrespondence,
                               @RequestParam("amount") double amount,
                               @RequestParam String description) {
        iTransferService.sendMoneyAndUpdateUsersAccounts(email, emailCorrespondence, description, amount);
    }

    @GetMapping(value = "/transfer")
    public String getAllTransferMadeByUser(@RequestParam String email) throws JsonProcessingException {
        List<String> data = iTransferService.getAllTransferFromACorrespondence(email);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);
    }

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
