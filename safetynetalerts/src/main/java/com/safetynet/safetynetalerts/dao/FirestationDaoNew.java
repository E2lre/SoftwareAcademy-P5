package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;

import java.util.List;

public interface FirestationDaoNew {
//    public List<Firestation> findAll();
//    //public Firestation finByStation (int station);
//    public Firestation findByStation(String station);
//    public Firestation save(Firestation firestation);



    public String getAddress();
    public void setAddress(String address);
    public String getStation();
    public void setStation(String station);
    public String toString() ;


}
