package com.safetynet.safetynetalerts.web.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicalRecordCanNotBeDeletedException extends Exception {
    private static final Logger logger = LogManager.getLogger(MedicalRecordCanNotBeDeletedException.class);
    public MedicalRecordCanNotBeDeletedException(String s) {
        super(s);
        logger.warn(s);
    }
}
