package com.safetynet.safetynetalerts.web.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class MedicalRecordCanNotBeModifyException extends Exception {
    private static final Logger logger = LogManager.getLogger(MedicalRecordCanNotBeModifyException.class);
    public MedicalRecordCanNotBeModifyException(String s) {
        super(s);
        logger.warn(s);
    }
}
