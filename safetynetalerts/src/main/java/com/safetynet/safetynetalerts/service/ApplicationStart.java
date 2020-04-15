package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FirestationDao;

import com.safetynet.safetynetalerts.model.Firestation;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ApplicationStart {
    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(ApplicationStart.class);
/*
    @Autowired
    private FirestationService firestationService;
*/
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private InputDataReader inputDataReader;

    @PostConstruct
    private void postConstruct() {

        logger.info("Start");

        //TEST TEST TEST TEST
        firestationDao=inputDataReader.readInputFile();
        logger.debug(firestationDao);
        //TEST TEST TEST TEST
   //     List<FirestationDaoNew> firestations = inputDataReader.readInputData();
 //       List<Firestation> firestations = inputDataReader.readInputDataXX();

        logger.debug("file read");
   //     firestationService = inputDataReader.loadFirestation(firestations);
 //       firestationDao  = inputDataReader.loadFirestationXX(firestations);
 //       logger.debug(firestations);
        logger.debug("Finish");
        logger.warn("Application started");
    }


}
