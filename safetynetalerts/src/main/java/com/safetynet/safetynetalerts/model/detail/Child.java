package com.safetynet.safetynetalerts.model.detail;

import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public class Child {

    private String firstName;
    private String lastName;
    private String age;

    public Child() {
    }

    public Child(String firstName, String lastName, String age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


}
