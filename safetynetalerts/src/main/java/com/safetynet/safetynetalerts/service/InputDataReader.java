package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FirestationDao;

import com.safetynet.safetynetalerts.model.Firestation;


import java.util.List;

public interface InputDataReader {
    public List<Firestation> readInputDataXX();
    public FirestationDao loadFirestationXX(List<Firestation> listFirestation);
    public FirestationDao readInputFile();

/*
    public List<FirestationDaoNew> readInputData();
    public FirestationService loadFirestation(List<FirestationDaoNew> listFirestationDao);
*/
}
