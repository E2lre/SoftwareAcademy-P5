package com.safetynet.safetynetalerts.web.controller;


import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    /*---------------------------  Get -----------------------------*/
    @GetMapping(value="/person")
    @ResponseStatus(HttpStatus.OK)
    public Person getPerson(@Valid @RequestBody Person person) throws PersonCanNotBeFoundException {

        logger.debug("Start");

        Person personResult = personService.get(person);

        if (personResult == null) {
            throw new PersonCanNotBeFoundException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be added");
        }

        logger.info("GET /person : " + personResult);
        logger.debug("Finish");
        return personResult;

    }
    /*---------------------------  Post -----------------------------*/
    @PostMapping(value="/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@Valid @RequestBody Person person) throws PersonCanNotbeAddedException {

       logger.debug("Start");

        Person personResult = personService.add(person);

        if (personResult == null) {
            throw new PersonCanNotbeAddedException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be added");
        }

        logger.info("POST /person : " + personResult);
        logger.debug("Finish");
        return personResult;

    }
    /*---------------------------  Put -----------------------------*/

    @PutMapping(value="/person")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Person modifyPerson(@Valid @RequestBody Person person) throws PersonCanNotBeModifyException {

        logger.debug("Start");


        Person personResult = personService.update(person);

        if (personResult == null) {
            throw new PersonCanNotBeModifyException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be modified");
        }

        logger.info("PUT /person : " + personResult);
        logger.debug("Finish");
        return personResult;

    }

    /*---------------------------  Delete -----------------------------*/

    @DeleteMapping(value="/person")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> deletePerson(@Valid @RequestBody Person person) throws PersonCanNotBeDeletedException {

        logger.debug("Start");


        List<Person> personList = personService.delete(person);

        if ((personList == null) || (personList.size()==0)) {
            throw new PersonCanNotBeDeletedException("FirstName " + person.getFirstName() + " lastname " + person.getLastName() +" Can not be deleted");
        }

        logger.info("Delete /person : " + personList );
        logger.debug("Finish");
        return personList;

    }
}
