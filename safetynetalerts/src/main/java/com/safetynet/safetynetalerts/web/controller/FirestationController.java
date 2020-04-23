package com.safetynet.safetynetalerts.web.controller;
import com.safetynet.safetynetalerts.model.Firestation;

import com.safetynet.safetynetalerts.model.detail.StationNumberInfo;
import com.safetynet.safetynetalerts.service.InputDataReader;

import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.web.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.util.List;



@RestController
public class FirestationController {

    private static final Logger logger = LogManager.getLogger(FirestationController.class);

    @Autowired
    private FirestationService firestationService;
    @Autowired
    private InputDataReader inputDataReader;



    /*---------------------------------------- GET list use to manualy check the loader -------------------------------*/

    /**
     * get the list of Firestation in memory
     * @return list of Firestation
     */
    @GetMapping(value="/firestations")
    public List<Firestation> listOfFirestations() {
        logger.info("GET/firestations");
        List<Firestation> firestationList = firestationService.findAll() ;
        logger.info("GET /firestations : " + firestationList);
        return firestationList;
    }

    /*---------------------------------------- GET by stationNumber -------------------------------*/

    /**
     * get a StationNumberInfo by station Number
     * @param station station number to be analyse
     * @return a StationNumberInfo
     * @throws FirestationNotFoundException in case of error
     */
    @GetMapping(value="/firestation")
    @ResponseBody
    public StationNumberInfo getFirestationByStation(@RequestParam(name = "stationNumber") String station) throws FirestationNotFoundException {

        logger.info("GET/firestation?stationNumber="+station);

        StationNumberInfo stationNumberInfo = firestationService.getPersonByStation(station);

        if ((stationNumberInfo.getPersons()==null)||((stationNumberInfo.getNbAdulte().equals("0"))&&(stationNumberInfo.getNbChild().equals("0")))) {
            throw new FirestationNotFoundException("no person for station " + station );
        }

        logger.info("GET /firestation?stationNumber : "+stationNumberInfo);

        return stationNumberInfo;


    }
    /*---------------------------------------- POST -------------------------------*/
    /**
     * Add a new firestation
     * @param firestation to be add
     * @return firestationadded
     * @throws FirestationCanNotbeAddedException in case of error
     */
    @PostMapping(value="/firestation")
    @ResponseStatus(HttpStatus.CREATED)
    public Firestation addFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotbeAddedException {

        logger.info("POST/firestation=" + firestation);

        Firestation firestationResult = firestationService.save(firestation);

      if (firestationResult == null) {
            throw new FirestationCanNotbeAddedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be added");
        }

        logger.info("POST /firestation : " + firestationResult);

        return firestationResult;
    }
    /*---------------------------------------- PUT -------------------------------*/
    /**
     * update a firestation
     * @param firestation to be update
     * @return the firestation updated
     * @throws FirestationCanNotBeModifyException in cas of error
     */
    @PutMapping(value="/firestation")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Firestation modifyFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotBeModifyException {


        logger.info("PUT/firestation=" + firestation);

        Firestation firestationResult = firestationService.updateByAddress(firestation);

        if (firestationResult == null) {
            throw new FirestationCanNotBeModifyException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be modify");
        }

        logger.info("PUT /firestation : " + firestationResult);
        return firestationResult;
    }
    /*---------------------------------------- DELETE -------------------------------*/
    /**
     * Delete a fire station : the key is the address
     * @param firestation to be delete
     * @return firestation delete
     * @throws FirestationCanNotBeDeletedException in case of error
     */
    @DeleteMapping(value="/firestation")
    @ResponseStatus(HttpStatus.OK)
    public List<Firestation> deleteFirestation(@Valid @RequestBody Firestation firestation) throws FirestationCanNotBeDeletedException {


        logger.info("Delete/firestation=" + firestation );


         List<Firestation> firestationList = firestationService.delete(firestation);

        if ((firestationList == null) || (firestationList.size()==0)) {
            throw new FirestationCanNotBeDeletedException("station " + firestation.getStation() + " address " + firestation.getAddress() +" Can not be deleted");
        }

        logger.info("Delete /firestation : " + firestationList );

        return firestationList;
    }

}
