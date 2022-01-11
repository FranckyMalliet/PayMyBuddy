package com.paymybuddy.application.controller;

import com.paymybuddy.application.service.ISecurityService;
import com.paymybuddy.application.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransferController {

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

    @PutMapping(value="/sendingMoneyToBank/transfer")
    public void sendingMoneyToBank(@RequestParam double amount) {
        iTransferService.sendMoneyToBankAccount(amount);
    }

    @PutMapping(value="/gettingMoneyFromBank/transfer")
    public void gettingMoneyFromBank(@RequestParam double amount) {
        iTransferService.getMoneyFromBankAccount(amount);
    }
}
