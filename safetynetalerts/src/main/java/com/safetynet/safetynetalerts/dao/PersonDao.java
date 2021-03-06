package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public interface PersonDao {


    public Person get (Person person);
    public Person add (Person person);
    public Person update (Person person);
    public List<Person> delete (Person person);
    public boolean load (List<Person> personList);
    public boolean clear();
    public List<Person> getPersonByAdress (List<String> address);
    public List<Person> getPersonByName (List<Person> personList);
    public List<Person> getPersonByCity (String city);
}
