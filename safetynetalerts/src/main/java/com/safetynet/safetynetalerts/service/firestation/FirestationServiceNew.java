package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.model.Firestation;

import java.util.List;

public interface FirestationServiceNew {
    public List<Firestation> findAll();
    public Firestation findByStation (String station);
    public Firestation save (Firestation firestation);
  //  public List<Firestation> load (List<Firestation> firestationList);
}
