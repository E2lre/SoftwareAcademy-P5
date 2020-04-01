package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;

import java.util.List;

public interface InputDataReader {
    public List<Firestation> readInputData();
    public FirestationDao loadFirestation(List<Firestation> listFirestation);
}
