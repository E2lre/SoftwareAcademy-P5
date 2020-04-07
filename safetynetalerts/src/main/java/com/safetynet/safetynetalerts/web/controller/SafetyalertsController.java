package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.dao.FirestationDao;

import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.web.exceptions.FirestationCanNotbeAddedException;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import javax.validation.Valid;
import java.util.List;



@RestController
public class SafetyalertsController {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private InputDataReader inputDataReader;


	
//TODO DAO avec le cours https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot

    //TODO a supprimer
    @GetMapping(value="/firestations")
    public List<Firestation> listOfFirestations() {
        return firestationDao.findAll();
    }

    @GetMapping(value="/firestation")
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

    @PostMapping(value="/firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public Firestation addFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotbeAddedException {

        logger.info("Start");
        //logger.debug("station ask : " + station);
        Firestation firestationResult = firestationDao.save(firestation);
//TODO
      if (firestationResult == null) {
            throw new FirestationCanNotbeAddedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be added");
        }

        //logger.debug(firestation);
        logger.info("Finish");
        return firestationResult;
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
