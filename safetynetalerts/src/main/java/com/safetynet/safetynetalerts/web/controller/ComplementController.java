package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.detail.Email;
import com.safetynet.safetynetalerts.model.detail.Fire;
import com.safetynet.safetynetalerts.model.detail.Flood;
import com.safetynet.safetynetalerts.model.detail.PersonInfo;
import com.safetynet.safetynetalerts.service.complement.ComplementService;
import com.safetynet.safetynetalerts.web.exceptions.NoEmailForCityException;
import com.safetynet.safetynetalerts.web.exceptions.NoFireAtThisAddressException;
import com.safetynet.safetynetalerts.web.exceptions.NoFloodForStationListException;
import com.safetynet.safetynetalerts.web.exceptions.NoPersonInfoForFisrtNameMastNameException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ComplementController {

    private static final Logger logger = LogManager.getLogger(ComplementController.class);
    @Autowired
    private ComplementService complementService;

    /*---------------------------  Get FireList ----------------------------*/
    @GetMapping(value="/fire")
    @ResponseStatus(HttpStatus.OK)
    public List<Fire> getFireListByAddress(@RequestParam(name = "address") String address) throws NoFireAtThisAddressException {
        logger.debug("Start");

        List<Fire> fireListResult = complementService.getFireByAddress(address);

        if(fireListResult.size() == 0)  {

            throw new NoFireAtThisAddressException("No Fire at the address : "+ address);
        }

        logger.info("GET /fire : " + fireListResult);
        logger.debug("Finish");
        return fireListResult;
    }

    /*---------------------------  Get FloodList ----------------------------*/
    @GetMapping(value="/flood")
    @ResponseStatus(HttpStatus.OK)
    public List<Flood> getFloodByStationList(@RequestParam(name = "stations") List<String> stations) throws NoFloodForStationListException {
        logger.debug("Start");

        List<Flood> floodListResult = complementService.getFloodByStationList(stations);

        if(floodListResult.size() == 0)  {

            throw new NoFloodForStationListException("No Flood for this station list : "+ stations);
        }

        logger.info("GET /flood : " + floodListResult);
        logger.debug("Finish");
        return floodListResult;
    }

    /*---------------------------  Get PersonInfoList ----------------------------*/
    @GetMapping(value="/personInfo")
    @ResponseStatus(HttpStatus.OK)
    public List<PersonInfo> getPersonInfoByFisrtNameLastName(@RequestParam ( name = "firstName",  required=false) String firstName, @RequestParam(name = "lastName") String lastName ) throws NoPersonInfoForFisrtNameMastNameException {
        logger.debug("Start");

        List<PersonInfo> personInfoListResult = complementService.getPersonInfoByFisrtNameLastName(firstName,lastName);

        if(personInfoListResult.size() == 0)  {

            throw new NoPersonInfoForFisrtNameMastNameException("No personInfo for firstName : "+ firstName + " and lastName : "+ lastName);
        }

        logger.info("GET /personInfo : " + personInfoListResult);
        logger.debug("Finish");
        return personInfoListResult;
    }
    /*---------------------------  Get EmailList ----------------------------*/
    @GetMapping(value="/communityEmail")
    @ResponseStatus(HttpStatus.OK)
    public List<Email> getEmailByCity(@RequestParam ( name = "city") String city) throws NoEmailForCityException {
        logger.debug("Start");

        List<Email> emailListResult = complementService.getEmailByCity(city);

        if(emailListResult.size() == 0)  {

            throw new NoEmailForCityException("No Email for city : "+ city);
        }

        logger.info("GET /communityEmail : " + emailListResult);
        logger.debug("Finish");
        return emailListResult;
    }
}
