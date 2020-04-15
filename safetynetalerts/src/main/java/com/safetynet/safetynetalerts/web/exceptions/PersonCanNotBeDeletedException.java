package com.safetynet.safetynetalerts.web.exceptions;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonCanNotBeDeletedException extends Exception {

    private static final Logger logger = LogManager.getLogger(PersonCanNotBeDeletedException.class);
    public PersonCanNotBeDeletedException(String s) {
        super(s);
        logger.warn(s);
    }
}