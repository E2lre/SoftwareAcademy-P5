package com.safetynet.safetynetalerts.service.person;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public interface PersonService {
    public Person get (Person person);
    public Person add (Person person);
    public Person update (Person person);
    public List<Person> delete (Person person);
}
