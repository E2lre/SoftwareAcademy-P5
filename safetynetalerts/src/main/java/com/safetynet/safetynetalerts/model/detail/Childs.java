package com.safetynet.safetynetalerts.model.detail;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public class Childs {
    private List<Child> childs;
    private List<Person> persons; //Other person

    public Childs() {

    }
    public Childs(List<Child> childs, List<Person> persons) {
        this.childs = childs;
        this.persons = persons;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }


}
