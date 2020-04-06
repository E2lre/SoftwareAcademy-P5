package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ApplicationStart {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private InputDataReader inputDataReader;

    @PostConstruct
    private void postConstruct() {

        logger.trace("Start");

        List<Firestation> firestations = inputDataReader.readInputData();
        firestationDao = inputDataReader.loadFirestation(firestations);
        logger.debug(firestations);
        logger.trace("Finish");
        logger.warn("Application started");
    }


}
