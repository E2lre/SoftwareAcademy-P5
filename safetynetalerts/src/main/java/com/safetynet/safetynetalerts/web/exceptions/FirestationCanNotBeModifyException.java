package com.safetynet.safetynetalerts.web.exceptions;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class FirestationCanNotBeModifyException extends Exception {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    public FirestationCanNotBeModifyException(String s) {
        super(s);
        logger.warn(s);
    }
}
