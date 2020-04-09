package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationDaoImpl implements FirestationDao {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

    public static List<Firestation> firestations = new ArrayList<>();

    //TODO : a retirer quand la lecture du fichier de data sera opérationnelle DEBUT
 //   static {
       // firestations.add(new Firestation(new String ("1509 Culver St"), 3));
        //firestations.add(new Firestation(new String ("29 15th St"), 2));
        //firestations.add(new Firestation(new String ("834 Binoc Ave"), 3));
  //  }

    //TODO : a retirer quand la lecture du fichier de data sera opérationnelle FIN

    @Override
    public List<Firestation> findAll() {
        return firestations;
    }

    @Override

    public Firestation findByStation(String station) {
        logger.trace("start");
        Firestation localFirestation = null;
        for(Firestation firestation : firestations){
            if (firestation.getStation().equals(station)){
                localFirestation = firestation;
            }
        }
        logger.trace("Finish");
        return localFirestation;
    }

    @Override
    public Firestation findByAddress(String address) {
        logger.trace("start");
        Firestation localFirestation = null;
        for(Firestation firestation : firestations){
            if (firestation.getAddress().equals(address)){
                localFirestation = firestation;
            }
        }
        logger.trace("Finish");
        return localFirestation;
    }

    @Override
    public Firestation save(Firestation firestation) {
        logger.trace("start");
        firestations.add(firestation);
        //TODO : ajouter Déjà existe
        logger.trace("Finish");
        return firestation;
    }

    /**
     * update a station by a number station. only address will be updated. Number station is unchange
     * @param firestation
     * @return firestation if OK, null else
     */
    @Override
    public Firestation updateByStation(Firestation firestation) {
        logger.trace("start");
        Firestation localFirestation = findByStation(firestation.getStation()); //find the station with right number station
        if (localFirestation != null) {
            int position = firestations.indexOf(localFirestation);
            if (position <0) {    //station not found
                firestation = null;
            }
            else {
                firestations.set(position,firestation);
            }
         }
        else {
            firestation = null;
        }
        logger.trace("Finish");
        return firestation;
    }
    @Override
    public Firestation updateByAddress(Firestation firestation) {
        logger.trace("start");

        Firestation localFirestation = findByAddress(firestation.getAddress()); //find the station with right number station
        if (localFirestation != null) {
            int position = firestations.indexOf(localFirestation);
            if (position <0) {    //station not found
                firestation = null;
            }
            else {
                firestations.set(position,firestation);
            }
        }
        else {
            firestation = null;
        }
        logger.trace("Finish");
        return firestation;
    }
}
