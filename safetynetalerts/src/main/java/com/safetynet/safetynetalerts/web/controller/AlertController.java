package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.detail.Childs;
import com.safetynet.safetynetalerts.model.detail.Phone;
import com.safetynet.safetynetalerts.service.alert.AlertService;

import com.safetynet.safetynetalerts.web.exceptions.NoChildAtThisAddressException;
import com.safetynet.safetynetalerts.web.exceptions.NoPhoneAtThisAddressException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class AlertController {
    private static final Logger logger = LogManager.getLogger(AlertController.class);
    @Autowired
    private AlertService alertService;

    /*---------------------------  Get childAlert ----------------------------*/

    /**
     * get a child List by Address
     * @param address to be analyse
     * @return list of child
     * @throws NoChildAtThisAddressException in case of error
     */
    @GetMapping(value="/childAlert")
    @ResponseStatus(HttpStatus.OK)
    public Childs getChild(@RequestParam(name = "address") String address) throws NoChildAtThisAddressException {

        logger.info("GET/childAlert?address=" + address);

        Childs childListResult = alertService.getChildByAddress(address);

        if ((childListResult.getChilds()==null)&&(childListResult.getPersons()==null)) {

            throw new NoChildAtThisAddressException("No Child at the address : "+ address);
        }

        logger.info("GET /childAlert : " + childListResult);

        return childListResult;


    }

    /*---------------------------  Get phoneAlert ----------------------------*/

    /**
     * get a list of phone for a station Number
     * @param station station number to be analyse
     * @return list of phone
     * @throws NoPhoneAtThisAddressException in case of error
     */
    @GetMapping(value="/phoneAlert")
    @ResponseStatus(HttpStatus.OK)
    public List<Phone> getPhone(@RequestParam(name = "station") String station) throws NoPhoneAtThisAddressException {

        logger.info("GET/phonedAlert?station=" + station);
        List<Phone> phoneListResult = alertService.getPhoneByStation(station);

        if ((phoneListResult == null) ||(phoneListResult.isEmpty())) {
            throw new NoPhoneAtThisAddressException("No Phone at the station : "+ station);
        }

        logger.info("GET /phonedAlert : " + phoneListResult);

        return phoneListResult;

    }
}
