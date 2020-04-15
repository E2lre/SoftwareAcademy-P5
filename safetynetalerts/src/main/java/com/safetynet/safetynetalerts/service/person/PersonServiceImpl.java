package com.safetynet.safetynetalerts.service.person;


import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;

    @Override
    public Person get (Person person){
        logger.debug("Start/finish");
        return personDao.get(person);
    }
    @Override
    public Person add(Person person) {
        logger.debug("Start/finish");
        return personDao.add(person);
    }

    @Override
    public Person update(Person person) {
        logger.debug("Start/finish");
        return personDao.update(person);
    }

    @Override
    public List<Person> delete(Person person) {
        logger.debug("Start/finish");
        return personDao.delete(person);
    }
}
