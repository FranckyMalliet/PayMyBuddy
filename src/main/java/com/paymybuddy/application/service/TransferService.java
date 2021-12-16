package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.model.Transfer;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService implements ITransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICorrespondenceService iCorrespondenceService;

    public Transfer addNewTransfer(Transfer transfer){
        return transferRepository.save(transfer);
    }

    public void sendMoneyAndUpdateUsersAccounts(String email, String emailCorrespondence,  String description, double amount){

        User userSendingMoney = iUserService.getUserDataFromEmail(email);
        User userGettingMoney = iUserService.getUserDataFromEmail(emailCorrespondence);

        if(userSendingMoney.getAccount() >= amount + collectFee(amount)){
            Correspondence correspondenceUserSendingMoney = iCorrespondenceService.getCorrespondenceFromUser(email, emailCorrespondence);

            Transfer transfer = new Transfer();
            transfer.setDescription(description);
            transfer.setAmount(amount);
            transfer.setTransactionFee(collectFee(amount) - amount);
            transfer.setCorrespondence(iCorrespondenceService.getCorrespondenceFromUser(email, emailCorrespondence));

            correspondenceUserSendingMoney.addTransfer(transfer);
            addNewTransfer(transfer);

            iUserService.updateUser(userSendingMoney.getAccount() - collectFee(amount), email);
            iUserService.updateUser(userGettingMoney.getAccount() + amount, emailCorrespondence);
        } else {
            System.out.println("You doesn't have enough money to proceed to the payment");
        }

    }

    public void sendMoneyToBankAccount(String email, double amount){

        User user = iUserService.getUserDataFromEmail(email);
        if(user.getAccount() >= amount){
            iUserService.updateUser(user.getAccount() - amount, email);
        } else {
            System.out.println("You doesn't have enough money to proceed to the payment");
        }
    }

    public void getMoneyFromBankAccount(String email, double amount){

        User user = iUserService.getUserDataFromEmail(email);
        iUserService.updateUser(user.getAccount() + amount, email);
    }

    public List<String> getAllTransferFromACorrespondence(String email){

        List<String> transferAllData = new ArrayList<>();

        List<Correspondence> correspondenceList = iCorrespondenceService.getAllCorrespondenceFromUser(email);
        for (Correspondence correspondenceData : correspondenceList)
        {
            List<Transfer> transferList = transferRepository.findAllTransferFromCorrespondence(correspondenceData.getIdCorrespondence());
            for (Transfer transferData : transferList) {
                transferAllData.add(transferData.getIdTransfer() + " " + transferData.getAmount() + " " + transferData.getDescription());
            }
        }
        return transferAllData;
    }

    private double collectFee(double account){
        double fee = (account*0.5)/100;
        return account + fee;
    }
}
