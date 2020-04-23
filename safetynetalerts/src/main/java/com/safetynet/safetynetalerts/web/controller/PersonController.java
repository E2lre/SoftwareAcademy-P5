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
//TODO PersonAPI
public class PersonController {

    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;

    /*---------------------------  Get -----------------------------*/

    /**
     * get a person
     * @param person firstname and lastname are mandatory
     * @return the person record
     * @throws PersonCanNotBeFoundException in case of error
     */
    @GetMapping(value="/person")
    @ResponseStatus(HttpStatus.OK)
    public Person getPerson(@Valid @RequestBody Person person) throws PersonCanNotBeFoundException {

        logger.info("GET/person=" + person);

        Person personResult = personService.get(person);

        if (personResult == null) {
            throw new PersonCanNotBeFoundException("FirstName " + person.getFirstName() + " lastName " + person.getLastName() +" Can not be found");
        }

        logger.info("GET /person : " + personResult);
        return personResult;

    }
    /*---------------------------  Post -----------------------------*/

    /**
     * add a person
     * @param person to be add
     * @return the person added
     * @throws PersonCanNotbeAddedException in case of error
     */
    @PostMapping(value="/person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person addPerson(@Valid @RequestBody Person person) throws PersonCanNotbeAddedException {


        logger.info("POST/person=" + person);

        Person personResult = personService.add(person);

        if (personResult == null) {
            throw new PersonCanNotbeAddedException("FirstName " + person.getFirstName() + " lastName " + person.getLastName() +" Can not be added");
        }

        logger.info("POST /person : " + personResult);

        return personResult;

    }
    /*---------------------------  Put -----------------------------*/

    /**
     * update a person
     * @param person to be updated
     * @return the person updated
     * @throws PersonCanNotBeModifyException in case off error
     */
    @PutMapping(value="/person")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Person modifyPerson(@Valid @RequestBody Person person) throws PersonCanNotBeModifyException {

        logger.info("PUT/person=" + person);

        Person personResult = personService.update(person);

        if (personResult == null) {
            throw new PersonCanNotBeModifyException("FirstName " + person.getFirstName() + " lastName " + person.getLastName() +" Can not be modified");
        }

        logger.info("PUT /person : " + personResult);

        return personResult;

    }

    /*---------------------------  Delete -----------------------------*/

    /**
     * delete a person
     * @param person to be delete
     * @return person deleted
     * @throws PersonCanNotBeDeletedException in case of error
     */
    @DeleteMapping(value="/person")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> deletePerson(@Valid @RequestBody Person person) throws PersonCanNotBeDeletedException {

        logger.info("Delete/person=" + person );

        List<Person> personList = personService.delete(person);

        if ((personList == null) || (personList.size()==0)) {
            throw new PersonCanNotBeDeletedException("FirstName " + person.getFirstName() + " lastName " + person.getLastName() +" Can not be deleted");
        }

        logger.info("Delete /person : " + personList );

        return personList;

    }
}
