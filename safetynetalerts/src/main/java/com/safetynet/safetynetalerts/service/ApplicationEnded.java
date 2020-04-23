package com.safetynet.safetynetalerts.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

@Component
public class ApplicationEnded {

    private static final Logger logger = LogManager.getLogger(ApplicationEnded.class);

    @PreDestroy
    public void preDestroy() {
        logger.info("Application terminated");
    }
}
