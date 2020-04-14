package com.safetynet.safetynetalerts.model;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

public class Firestations {
    private List<Firestation> firestations = null;
    public Firestations() {

    }
    public Firestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

     public List<Firestation> getFirestations() {
        return firestations;
    }

    public void setFirestations(List<Firestation> firestations) {
        this.firestations = firestations;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
