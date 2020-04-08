package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDaoNew;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationServiceImpl implements FirestationService {


    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

    public static List<FirestationDaoNew> firestations = new ArrayList<>();

    @Override
    public List<FirestationDaoNew> findAll() {
        return firestations;
    }

    FirestationServiceImpl ( List<FirestationDaoNew> lFirestation) {
        firestations = lFirestation;
    }

    @Override
    //public Firestation finByStation(int station) {
    public FirestationDaoNew findByStation(String station) {
        logger.debug("start");
        FirestationDaoNew localFirestation = null;
        for(FirestationDaoNew firestationDao : firestations){
            if (firestationDao.getStation().equals(station)){
                localFirestation = firestationDao;
            }
        }
        logger.debug("Finish");
        return localFirestation;
    }


    @Override
    public FirestationDaoNew save(FirestationDaoNew firestationDao) {
        logger.debug("start");
        firestations.add(firestationDao);
        //TODO : ajouter Déjà existe
        logger.debug("Finish");
        return firestationDao;
    }
    @Override
    public List<FirestationDaoNew> load (List<FirestationDaoNew> firestationList) {
        logger.debug("start");
        firestations = firestationList;
        //TODO : ajouter Déjà existe
        logger.debug("Finish");
        return firestationList;
    }
}
