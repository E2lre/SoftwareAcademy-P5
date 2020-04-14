package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.FirestationCanNotbeAddedException;
import com.safetynet.safetynetalerts.web.exceptions.PersonCanNotbeAddedException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Autowired
    private PersonService personService;

    @PostMapping(value="/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@Valid @RequestBody Person person) throws PersonCanNotbeAddedException {

        logger.trace("Start");
        //logger.debug("station ask : " + station);
        Person personResult = PersonService.add(person);
//TODO A tester
        if (personResult == null) {
            throw new PersonCanNotbeAddedException("FirstName " + person.getFirstName()() + " lastname " + person.getLastName() +" Can not be added");
        }

        logger.info("POST /person : " + personResult);
        logger.trace("Finish");
        return personResult;
    }
}
