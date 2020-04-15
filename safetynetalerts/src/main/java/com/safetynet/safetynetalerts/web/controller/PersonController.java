package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.service.firestation.FirestationServiceImpl;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.*;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {
    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    @PostMapping(value="/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@Valid @RequestBody Person person) throws PersonCanNotbeAddedException {

/*        logger.debug("Start");

        Person personResult = personService.add(person);
//TODO A tester
        if (personResult == null) {
            throw new PersonCanNotbeAddedException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be added");
        }

        logger.info("POST /person : " + personResult);
        logger.debug("Finish");
        return personResult;*/
        return null;

    }

    @PutMapping(value="/person")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Person modifyPerson(@Valid @RequestBody Person person) throws PersonCanNotBeModifyException {
/*
        logger.debug("Start");


        Person personResult = personService.update(person);
//TODO A tester
        if (personResult == null) {
            throw new PersonCanNotBeModifyException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be modified");
        }

        logger.info("PUT /person : " + personResult);
        logger.debug("Finish");
        return personResult;*/
        return null;
    }


    @DeleteMapping(value="/person")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> deleteFirestation(@Valid @RequestBody Person person) throws PersonCanNotBeDeletedException {
/*
        logger.debug("Start");


        List<Person> personList = personService.delete(person);
//TODO A tester
        if ((personList == null) || (personList.size()==0)) {
            throw new PersonCanNotBeDeletedException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be deleted");
        }

        logger.info("Delete /person : " + personList );
        logger.debug("Finish");
        return personList;*/
        return null;
    }
}
