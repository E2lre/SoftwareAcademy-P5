package com.safetynet.safetynetalerts.web.exceptions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class FirestationCanNotbeAddedException extends Exception {


    private static final Logger logger = LogManager.getLogger(FirestationCanNotbeAddedException.class);

    public FirestationCanNotbeAddedException(String s) {
        super(s);
        logger.error(s);
    }
}
