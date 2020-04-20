package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Phone;
import com.safetynet.safetynetalerts.service.alert.AlertService;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.FirestationNotFoundException;
import com.safetynet.safetynetalerts.web.exceptions.NoChildAtThisAddressException;
import com.safetynet.safetynetalerts.web.exceptions.NoPhoneAtThisAddressException;
import com.safetynet.safetynetalerts.web.exceptions.PersonCanNotBeFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AlertController {
    private static final Logger logger = LogManager.getLogger(PersonController.class);
    @Autowired
    private AlertService alertService;

    /*---------------------------  Get childAlert ----------------------------*/
    @GetMapping(value="/childAlert")
    @ResponseStatus(HttpStatus.OK)
    public List<Person> getChild(@RequestParam(name = "address") String address) throws NoChildAtThisAddressException {

        logger.debug("Start");

        List<Person> childListResult = alertService.getChildByAddress(address);

        if ((childListResult == null) ||(childListResult.isEmpty())) {
            throw new NoChildAtThisAddressException("No Child at the address : "+ address);
        }

        logger.info("GET /childAlert : " + childListResult);
        logger.debug("Finish");
        return childListResult;

    }

    /*---------------------------  Get phoneAlert ----------------------------*/
    //TODO MODIFIER PAR STATION
    @GetMapping(value="/phoneAlert")
    @ResponseStatus(HttpStatus.OK)
    public List<Phone> getPhone(@RequestParam(name = "station") String station) throws NoPhoneAtThisAddressException {

        logger.debug("Start");

        List<Phone> phoneListResult = alertService.getPhoneByStation(station);

        if ((phoneListResult == null) ||(phoneListResult.isEmpty())) {
            throw new NoPhoneAtThisAddressException("No Phone at the station : "+ station);
        }

        logger.info("GET /phonedAlert : " + phoneListResult);
        logger.debug("Finish");
        return phoneListResult;

    }
}
