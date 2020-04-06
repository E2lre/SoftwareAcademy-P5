package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.dao.FirestationDao;

import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import java.util.List;



@RestController
public class SafetyalertsController {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private InputDataReader inputDataReader;


	
//TODO DAO avec le cours https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot



    @GetMapping(value="/Firestation")
    @ResponseBody
    public Firestation showFirestationByStation(@RequestParam(name = "stationNumber") String station) throws FirestationNotFoundException {

        logger.trace("Start");
        //logger.debug("station ask : " + station);
        Firestation firestation = firestationDao.findByStation(station);
        if (firestation == null) {
            throw new FirestationNotFoundException("station " + station + " does not exist");
        }

        //logger.debug(firestation);
        logger.trace("Finish");
        return firestation;
    }


    //TODO a supprimer
    @GetMapping(value="/Firestations2")
    public List<Firestation> listOfFirestations() {
        return firestationDao.findAll();
    }

    //TODO a supprimer
    @GetMapping(value="/Firestations/{station}")
    public Firestation showFirestationsByStation(@PathVariable String station) throws FirestationNotFoundException {
                logger.trace("Start");

        Firestation firestation = firestationDao.findByStation(station);
        if (firestation == null) {
            throw new FirestationNotFoundException("station " + station + " does not exist");
        }

        //logger.debug(firestation);
        logger.trace("Finish");
        return firestation;
    }
    //TODO a supprimer
    @GetMapping(value="/Firestations/load")
    public List<Firestation> loadFirestations() {
        logger.trace("Start");

        List<Firestation> firestations = inputDataReader.readInputData();
        firestationDao = inputDataReader.loadFirestation(firestations);
        logger.debug(firestations);
        logger.trace("Finish");
        return firestations;

    }
}
