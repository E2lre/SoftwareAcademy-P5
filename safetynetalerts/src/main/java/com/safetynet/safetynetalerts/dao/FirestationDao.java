package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;

import java.util.List;

public interface FirestationDao {
    public List<Firestation> findAll();
    //public Firestation finByStation (int station);
    public Firestation findByStation (String station);
    public Firestation save (Firestation firestation);



}
