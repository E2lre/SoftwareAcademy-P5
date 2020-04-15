package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(PersonDaoImpl.class);

    private List<Person> persons = new ArrayList<>();;

    public PersonDaoImpl() {

    }

    public PersonDaoImpl(List<Person> persons) {
        this.persons = persons;
    }


  /*  @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }
*/
    @Override
    public Person get (Person person){
        Person  resultPerson = null;
        return resultPerson;
    }

    @Override
    public Person add(Person person) {
        return null;
    }

    @Override
    public Person update(Person person) {
        return null;
    }

    @Override
    public List<Person> delete(Person person) {
        return null;
    }

    @Override
    public boolean load (List<Person> personList) {
        persons.addAll(personList);
//        for(Person ePerson : personList) {
//            persons.add(ePerson);
//
//        }
        return true;
    }
}
