package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;

import com.safetynet.safetynetalerts.service.InputDataReader;

import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.web.exceptions.FirestationCanNotBeDeletedException;
import com.safetynet.safetynetalerts.web.exceptions.FirestationCanNotbeAddedException;
import com.safetynet.safetynetalerts.web.exceptions.FirestationCanNotBeModifyException;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;

import javax.validation.Valid;
import java.util.List;


//TODO RENOMER CE CONTROLEUR

@RestController
public class SafetyalertsController {

    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private FirestationService firestationService;
    @Autowired
    private InputDataReader inputDataReader;


	
//TODO DAO avec le cours https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot

    //TODO a supprimer
    @GetMapping(value="/firestations")
    public List<Firestation> listOfFirestations() {
        List<Firestation> firestationList = firestationService.findAll() ;
        logger.info("GET /firestations : " + firestationList);
        return firestationList;
//        return firestationService.findAll();
    }

    @GetMapping(value="/firestation")
    @ResponseBody
    public Firestation showFirestationByStation(@RequestParam(name = "stationNumber") String station) throws FirestationNotFoundException {

        logger.trace("Start");
        //logger.debug("station ask : " + station);
        Firestation firestation = firestationService.findByStation(station);
        if (firestation == null) {
            throw new FirestationNotFoundException("station " + station + " does not exist");
        }

        logger.info("GET /firestation?stationNumber="+station+" : " + firestation);
        logger.trace("Finish");
        return firestation;
    }

    /**
     * Add a new firestation
     * @param firestation
     * @return firestationadded
     * @throws FirestationCanNotbeAddedException
     */
    @PostMapping(value="/firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public Firestation addFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotbeAddedException {

        logger.trace("Start");
        //logger.debug("station ask : " + station);
        Firestation firestationResult = firestationService.save(firestation);
//TODO A tester
      if (firestationResult == null) {
            throw new FirestationCanNotbeAddedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be added");
        }

        logger.info("POST /firestation : " + firestationResult);
        logger.trace("Finish");
        return firestationResult;
    }

    /**
     * update a firestation
     * @param firestation
     * @return the firestation updated
     * @throws FirestationCanNotBeModifyException
     */
    @PutMapping(value="/firestation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Firestation modifyFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotBeModifyException {

        logger.trace("Start");

        //Firestation firestationResult = firestationService.updateByStation(firestation);

        Firestation firestationResult = firestationService.updateByAddress(firestation);
//TODO A tester
        if (firestationResult == null) {
            throw new FirestationCanNotBeModifyException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be modify");
        }

        logger.info("PUT /firestation : " + firestationResult);
        logger.trace("Finish");
        return firestationResult;
    }

    /**
     * Delete a fire station : the key is the address
     * @param firestation
     * @return firestation delete
     * @throws FirestationCanNotBeDeletedException
     */
    @DeleteMapping(value="/firestation")
    @ResponseStatus(HttpStatus.OK)
    public List<Firestation> deleteFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotBeDeletedException {

        logger.trace("Start");

        //Firestation firestationResult = firestationService.updateByStation(firestation);

         List<Firestation> firestationList = firestationService.delete(firestation);
//TODO A tester
        if ((firestationList == null) || (firestationList.size()==0)) {
            throw new FirestationCanNotBeDeletedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be deleted");
        }

        logger.info("Delete /firestation : " + firestationList );
        logger.trace("Finish");
        return firestationList;
    }

}
