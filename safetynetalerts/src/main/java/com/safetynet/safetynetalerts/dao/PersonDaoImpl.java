package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private static final Logger logger = LogManager.getLogger(PersonDaoImpl.class);

    private static List<Person> persons = new ArrayList<>();;

    public PersonDaoImpl() {

    }

    public PersonDaoImpl(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Get a person
     * @param person firstName and lastName to find
     * @return the person ask, null if not found
     */
    @Override
    public Person get (Person person){
        logger.debug("start");
        Person  resultPerson = null;

        logger.debug(persons);

        for(Person ePerson : persons){
            if ((ePerson.getFirstName().equals(person.getFirstName()))&&(ePerson.getLastName().equals(person.getLastName()))){
                resultPerson = ePerson;
            }
        }
        logger.debug("Finish");
        return resultPerson;
    }

    /**
     * Add a person
     * @param person to add
     * @return person add, null if already exist
     */
    @Override
    public Person add(Person person) {
        logger.debug("start");
        Person  resultPerson = null;
        boolean personExist = false;

        //Detect if the person already exist
        for(Person ePerson : persons){
            if ((ePerson.getFirstName().equals(person.getFirstName()))&&(ePerson.getLastName().equals(person.getLastName()))){
                personExist = true;
            }
        }
        //If teh person doesn't exist, creat person
        if (!personExist) {
            persons.add(person) ;
            resultPerson = person;
        } else {
            logger.debug("Person already exist : FirstName :" + person.getFirstName()+" LastName :" + person.getLastName());
        }


        logger.debug("Finish");
        return resultPerson;
    }

    /**
     * update an existing person (FirstName + LastName is the key)
     * @param person to update
     * @return person updated, null if the person doesn't exist
     */
    @Override
    public Person update(Person person) {
        logger.debug("Start");
        Person  resultPerson = null;


        //Detect if the person already exist
        for(Person ePerson : persons){
            if ((ePerson.getFirstName().equals(person.getFirstName()))&&(ePerson.getLastName().equals(person.getLastName()))){
                int position = persons.indexOf(ePerson); //If the person exist, find the position in the list in memory and update
                if (position <0) {
                    resultPerson = null;
                } else {
                    persons.set(position,person);
                    resultPerson = person;
                }
            }
        }

        logger.debug("Finish");
        return resultPerson;
    }

    /**
     * delete an existing person
     * @param person person to delete (firstName + lastName is the key
     * @return person delete, else null
     */
    @Override
    public List<Person> delete(Person person) {

        logger.debug("start");
        List<Person>  resultPersonList = new ArrayList<>();


        //Detect if the person already exist
/*        for(Person ePerson : persons){
            if ((ePerson.getFirstName().equals(person.getFirstName()))&&(ePerson.getLastName().equals(person.getLastName()))){
                int position = persons.indexOf(ePerson); //If the person exist, find the position in the list in memory and delete
                if (position >=0)  {
                    resultPersonList.add(ePerson);
                    persons.remove(position);
                 }
            }
        }*/

        Iterator itr = persons.iterator();

        while (itr.hasNext()){
            Person indexPerson = (Person) itr.next();
            //Detect if the person already exist
            if ((indexPerson.getFirstName().equals(person.getFirstName())) && (indexPerson.getLastName().equals(person.getLastName()))){
                resultPersonList.add(indexPerson);
                itr.remove();
            }
        }

        logger.debug("Finish");
        return resultPersonList;

    }

    /**
     * Load list of person at start application
     * @param personList list of person to load
     * @return true if no errors
     */
    @Override
    public boolean load (List<Person> personList) {
        logger.debug("load-start");
        persons.addAll(personList);
//TODO gérer les erreurs
        logger.debug(persons);
        logger.debug("load-Finish");
        return true;
    }

    /**
     * clear the list of person in mémory
     * use by unit test
     * @return true if ok (always)
     */
    @Override
    public boolean clear(){
        persons.clear();
        return true;
    }

    @Override
    public List<Person> getPersonByAdress (List<String> addresses){
        logger.debug("start");
        List<Person> localPersonList = new ArrayList<>();
        for (String eAddress : addresses ) {
            for (Person ePerson : persons) {
                if (ePerson.getAddress().equals(eAddress)) {
                    localPersonList.add(ePerson);
                }
            }
        }
        logger.debug("Finish");
        return localPersonList;
    }

    @Override
    public List<Person> getPersonByName (List<Person> personList) {
        List<Person> personListResult = new ArrayList<>();
        for (Person ePerson : persons) {
            for (Person ePersonList : personList) {
                if ((ePersonList.getFirstName().equals(ePerson.getFirstName())) && (ePersonList.getLastName().equals(ePerson.getLastName()))) {
                    personListResult.add(ePerson);
                }
            }
         }
        return personListResult;
    }
}
