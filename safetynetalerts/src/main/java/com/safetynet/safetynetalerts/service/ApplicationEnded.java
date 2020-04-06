package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;

@Component
public class ApplicationEnded {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

    @PreDestroy
    public void preDestroy() {
        logger.warn("Application terminated");
    }
}
