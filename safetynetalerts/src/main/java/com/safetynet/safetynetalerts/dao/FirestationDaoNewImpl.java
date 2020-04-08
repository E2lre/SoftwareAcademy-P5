package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationDaoNewImpl implements FirestationDaoNew {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    public static Firestation firestation = new Firestation();
/*
    private String address;
    private String station;
*/
    //TODO : a retirer quand la lecture du fichier de data sera opérationnelle FIN


/*
    public FirestationDaoNewImpl(String address, String station){
        this.address = address;
        this.station = station;
        firestation.setAddress(address);
        firestation.setStation(station);
    }
*/

    @Override
    public String getAddress(){
        return firestation.getAddress();
    }
    @Override
    public void setAddress(String address) {
        firestation.setAddress(address);
    }
    @Override
    public String getStation(){
        return firestation.getStation();
    }
    @Override
    public void setStation(String station){
        firestation.setStation(station);
    }

    @Override
    public String toString(){
        return firestation.toString();
    }




}
