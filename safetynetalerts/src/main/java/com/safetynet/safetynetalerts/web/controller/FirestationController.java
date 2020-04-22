package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.StationNumberInfo;
import com.safetynet.safetynetalerts.service.InputDataReader;

import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.web.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//import org.apache.log4j.Logger;

import javax.validation.Valid;
import java.util.List;



@RestController
public class FirestationController {

    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    private FirestationService firestationService;
    @Autowired
    private InputDataReader inputDataReader;


	
//TODO DAO avec le cours https://openclassrooms.com/fr/courses/4668056-construisez-des-microservices/5122884-creez-un-microservice-grace-a-spring-boot

    //TODO a supprimer
    @GetMapping(value="/firestation/list")
    public List<Firestation> listOfFirestations() {
        List<Firestation> firestationList = firestationService.findAll() ;
        logger.info("GET /firestation/list : " + firestationList);
        return firestationList;
    }

    @GetMapping(value="/firestation")
    @ResponseBody
    public StationNumberInfo showFirestationByStation(@RequestParam(name = "stationNumber") String station) throws FirestationNotFoundException {

        logger.debug("Start");

        //logger.debug("station ask : " + station);
        StationNumberInfo stationNumberInfo = firestationService.getPersonByStation(station);
       // if (stationNumberInfo.getPersons().isEmpty()) {
        if ((stationNumberInfo.getPersons()==null)||((stationNumberInfo.getNbAdulte().equals("0"))&&(stationNumberInfo.getNbChild().equals("0")))) {
            throw new FirestationNotFoundException("no person for station " + station );
        }

        logger.info("GET /firestation?stationNumber="+stationNumberInfo);
        logger.debug("Finish");
        return stationNumberInfo;

        /*        logger.debug("Start");

        //logger.debug("station ask : " + station);
        List<Person> personList = firestationService.getPersonByStation(station);
        if (personList.isEmpty()) {
            throw new FirestationNotFoundException("no person for station " + station );
        }

        logger.info("GET /firestation?stationNumber="+personList);
        logger.debug("Finish");
        return personList;*/

 /*       logger.debug("Start");    //Ancien code
        //logger.debug("station ask : " + station);
        Firestation firestation = firestationService.findByStation(station);
        if (firestation == null) {
            throw new FirestationNotFoundException("station " + station + " does not exist");
        }

        logger.info("GET /firestation?stationNumber="+station+" : " + firestation);
        logger.debug("Finish");
        return firestation;*/
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

        logger.debug("Start");
        //logger.debug("station ask : " + station);
        Firestation firestationResult = firestationService.save(firestation);
//TODO A tester
      if (firestationResult == null) {
            throw new FirestationCanNotbeAddedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be added");
        }

        logger.info("POST /firestation : " + firestationResult);
        logger.debug("Finish");
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

        logger.debug("Start");

        //Firestation firestationResult = firestationService.updateByStation(firestation);

        Firestation firestationResult = firestationService.updateByAddress(firestation);
//TODO A tester
        if (firestationResult == null) {
            throw new FirestationCanNotBeModifyException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be modify");
        }

        logger.info("PUT /firestation : " + firestationResult);
        logger.debug("Finish");
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

        logger.debug("Start");

        //Firestation firestationResult = firestationService.updateByStation(firestation);

         List<Firestation> firestationList = firestationService.delete(firestation);
//TODO A tester
        if ((firestationList == null) || (firestationList.size()==0)) {
            throw new FirestationCanNotBeDeletedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be deleted");
        }

        logger.info("Delete /firestation : " + firestationList );
        logger.debug("Finish");
        return firestationList;
    }

}
