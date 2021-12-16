package com.paymybuddy.application.service;

import com.paymybuddy.application.model.Correspondence;

import java.util.List;

public interface ICorrespondenceService {

    //CRUD ENDPOINTS
    //CREATE
    public Correspondence addNewCorrespondenceToDatabase(Correspondence correspondence);
    public void createNewCorrespondence(String email, String emailCorrespondence);

    //READ
    public Correspondence getCorrespondenceFromUser(String email, String emailCorrespondence);

    //VIEW
    public List<Correspondence> getAllCorrespondenceFromUser(String email);
}
