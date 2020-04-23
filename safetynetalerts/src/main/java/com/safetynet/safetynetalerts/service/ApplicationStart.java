package com.safetynet.safetynetalerts.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApplicationStart {

    private static final Logger logger = LogManager.getLogger(ApplicationStart.class);

    @Autowired
    private InputDataReader inputDataReader;

    @PostConstruct
    private void postConstruct() {

        logger.info("Start Application");

        boolean resultat = inputDataReader.readInputFile();
        if (resultat) {
            logger.info("Application started Correctly");
        } else {
            logger.error("Application started Incorrectly");
        }
    }


}
