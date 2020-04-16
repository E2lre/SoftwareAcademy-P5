package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public interface FirestationService {
    public List<Firestation> findAll();
    public Firestation findByStation (String station);
    public Firestation save (Firestation firestation);
    public Firestation updateByStation(Firestation firestation);
    public Firestation updateByAddress(Firestation firestation);
    public List<Firestation> delete(Firestation firestation);
    public List<Person> getPersonByStation(String station);

    //  public List<Firestation> load (List<Firestation> firestationList);
}
