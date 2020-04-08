package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoNew;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;

import java.util.List;

public interface InputDataReader {
    public List<Firestation> readInputDataXX();
    public FirestationDao loadFirestationXX(List<Firestation> listFirestation);
    public List<FirestationDaoNew> readInputData();
    public FirestationService loadFirestation(List<FirestationDaoNew> listFirestationDao);
}
