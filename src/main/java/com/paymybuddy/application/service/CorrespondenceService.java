package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Correspondence;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.CorrespondenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CorrespondenceService implements ICorrespondenceService{

    @Autowired
    private CorrespondenceRepository correspondenceRepository;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISecurityService iSecurityService;

    //CRUD ENDPOINTS

    //CREATE
    public void createNewCorrespondence(String email, String emailCorrespondence){
        User user = iUserService.getUserDataFromEmail(email);

        Correspondence newCorrespondence = new Correspondence();
        newCorrespondence.setEmailCorrespondence(emailCorrespondence);

        user.addCorrespondence(newCorrespondence);

        if(Objects.isNull(iUserService.getUserDataFromEmail(emailCorrespondence))){
            System.out.println("This email " + emailCorrespondence + " doesn't exist");
        }
        else if(Objects.isNull(getCorrespondenceFromUser(email, emailCorrespondence))){
            addNewCorrespondenceToDatabase(newCorrespondence);
        }
        else {
            System.out.println("This correspondence at the id number "
                    + getCorrespondenceFromUser(email, emailCorrespondence).getIdCorrespondence() + " "
                    + newCorrespondence.getEmailCorrespondence() + " "
                    + newCorrespondence.getUser().getEmail() + " already exists in the database");
        }
    }

    public Correspondence addNewCorrespondenceToDatabase(Correspondence correspondence){
        return correspondenceRepository.save(correspondence);
    }

    //READ
    public Correspondence getCorrespondenceFromUser(String email, String emailCorrespondence){
        return correspondenceRepository.findUserCorrespondence(email, emailCorrespondence);
    }

    public List<Correspondence> getAllCorrespondenceFromUser(){
        String email = iSecurityService.getUserEmail();
        List<Correspondence> correspondenceList = correspondenceRepository.findAllCorrespondenceUser(email);
        return correspondenceList;
    }
}
