package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;

import java.util.List;

public interface FirestationDao {
    public List<Firestation> findAll();
    public Firestation findByStation (String station);
    public Firestation findByAddress (String address);
    public Firestation save (Firestation firestation);
    public Firestation updateByStation (Firestation firestation);
    public Firestation updateByAddress (Firestation firestation);
    public List<Firestation> delete(Firestation firestation);
    public boolean clear();
    public boolean load (List<Firestation> firestationDaoList);
    public List<Firestation> getFirestationListByStation (String station);


}
