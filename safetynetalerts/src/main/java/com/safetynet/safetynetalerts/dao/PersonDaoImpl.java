package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {
    private List<Person> persons = null;

    @Override
    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void setPersons(List<Person> persons) {
        this.persons = persons;
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

}
