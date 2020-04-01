package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.dao.FirestationDao;

import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.apache.log4j.Logger;
import java.util.List;



@RestController
public class SafetyalertsController {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private InputDataReader inputDataReader;

    //TODO a voir s'il faut supprimer
    @RequestMapping(value="/firestation", method=RequestMethod.GET)
	public String listFirestation() {
       return "la station demandée est : aucune " ;
    }

    //TODO a voir s'il faut supprimer
   @GetMapping(value="/firestation/{station}")
   public Firestation listFirestation(@PathVariable String station) {
      //Firestation firestation = new Firestation(new String( "Station"),station);
       Firestation firestation = new Firestation(new String( "Station"),station);
       return firestation;
  }
	
//TODO DAO avec le cours https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot
    @GetMapping(value="/Firestations")
    public List<Firestation> listOfFirestation() {
    return firestationDao.findAll();
    }

    @GetMapping(value="/Firestations/{station}")
    public Firestation showFirestationByStation(@PathVariable String station) throws FirestationNotFoundException {

        logger.trace("Start");

        Firestation firestation = firestationDao.findByStation(station);
        if (firestation == null) throw new FirestationNotFoundException("station " + station + " does not exist");

        logger.debug(firestation);
        logger.trace("Finish");
        return firestation;
    }

    @GetMapping(value="/Firestations/load")
    public List<Firestation> loadFirestation() {
        logger.trace("Start");

        List<Firestation> firestations = inputDataReader.readInputData();
        firestationDao = inputDataReader.loadFirestation(firestations);
        logger.debug(firestations);
        logger.trace("Finish");
        return firestations;

    }
}
