package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.alert.AlertService;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import com.safetynet.safetynetalerts.web.exceptions.NoChildAtThisAddressException;
import com.safetynet.safetynetalerts.web.exceptions.PersonCanNotBeFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AlertController {
    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private AlertService alertService;

    /*---------------------------  Get -----------------------------*/
    @GetMapping(value="/childAlert")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getPerson(@RequestParam(name = "address") String address) throws NoChildAtThisAddressException {

        logger.debug("Start");

        List<Person> childListResult = alertService.getChildByAddress(address);

        if ((childListResult == null) ||(childListResult.isEmpty())) {
            throw new NoChildAtThisAddressException("No Child at the address : "+ address);
        }

        logger.info("GET /childAlert : " + childListResult);
        logger.debug("Finish");
        return childListResult;

    }

}
