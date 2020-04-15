package com.safetynet.safetynetalerts.model;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Person {
    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(Person.class);

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;

    /**
     * Default constructor
     */
    public Person() {
    }

    /**
     *  Constructor
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param zip
     * @param phone
     * @param email
     */
    public Person(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }


    public static Logger getLogger() {
        return logger;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        logger.debug("start/finish");
        return "Person [firstName=" + firstName+ ", lastName=" + lastName + ", address=" + address +", city=" + city + ", zip=" + zip + ", phone=" + phone + ", email=" + email+ "]";
    }
}
