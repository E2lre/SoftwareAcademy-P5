package com.safetynet.safetynetalerts.model;

import java.util.List;

public class Persons {

    private List<Person> persons = null;

    public Persons() {

    }

    public Persons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    public String toString() {
        return "Persons{" +
                "persons=" + persons +
                '}';
    }
}
