package com.safetynet.safetynetalerts.model.detail;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe utilisée pour phoneAlert
 */
public class Phone {
    private static final Logger logger = LogManager.getLogger(Phone.class);


    private String firstName;
    private String lastName;
    private String phone;
    /**
     * Constructor for phone
     * @param firstName
     * @param lastName
     * @param phone
     */
    public Phone(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }

    /**
     * Constructor for phone
     */
    public Phone() {

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }


}
