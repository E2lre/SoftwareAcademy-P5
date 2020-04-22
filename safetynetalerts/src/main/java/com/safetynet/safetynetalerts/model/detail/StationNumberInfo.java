package com.safetynet.safetynetalerts.model.detail;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public class StationNumberInfo {
    private String nbAdulte;
    private String nbChild;
    private List<Person> persons;

    public StationNumberInfo() {

    }
    public StationNumberInfo(String nbAdulte, String nbChild, List<Person> persons) {
        this.nbAdulte = nbAdulte;
        this.nbChild = nbChild;
        this.persons = persons;
    }

    public String getNbAdulte () {
            return nbAdulte;
        }

    public void setNbAdulte (String nbAdulte){
            this.nbAdulte = nbAdulte;
        }

    public String getNbChild () {
            return nbChild;
        }

    public void setNbChild (String nbChild){
            this.nbChild = nbChild;
    }

    public List<Person> getPersons () {
            return persons;
    }

    public void setPersons (List < Person > persons) {
            this.persons = persons;
        }
}