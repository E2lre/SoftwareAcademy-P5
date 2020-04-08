package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDaoNew;

import java.util.List;

public interface FirestationService {
    public List<FirestationDaoNew> findAll();
    public FirestationDaoNew findByStation (String station);
    public FirestationDaoNew save (FirestationDaoNew firestation);
    public List<FirestationDaoNew> load (List<FirestationDaoNew> firestationList);
}
