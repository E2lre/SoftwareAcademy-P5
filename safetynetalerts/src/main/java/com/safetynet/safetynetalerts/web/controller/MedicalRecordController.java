package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.medicalrecord.MedicalRecordService;
import com.safetynet.safetynetalerts.service.person.PersonService;
import com.safetynet.safetynetalerts.web.exceptions.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MedicalRecordController {
    private static final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private MedicalRecordService medicalRecordService;

    /*---------------------------  Get -----------------------------*/
    @GetMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.OK)
    public MedicalRecord getPerson(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeFoundException {

        logger.debug("Start");

        MedicalRecord medicalRecordResult = medicalRecordService.get(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotBeFoundException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be added");
        }

        logger.info("GET /medicalReport : " + medicalRecordResult);
        logger.debug("Finish");
        return medicalRecordResult;

    }
    /*---------------------------  Post -----------------------------*/
    @PostMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecord addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotbeAddedException {

        logger.debug("Start");

        MedicalRecord medicalRecordResult = medicalRecordService.add(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotbeAddedException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be added");
        }

        logger.info("POST /medicalReport : " + medicalRecordResult);
        logger.debug("Finish");
        return medicalRecordResult;

    }
    /*---------------------------  Put -----------------------------*/

    @PutMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MedicalRecord modifyMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeModifyException {

        logger.debug("Start");


        MedicalRecord medicalRecordResult = medicalRecordService.update(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotBeModifyException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be modified");
        }

        logger.info("PUT /medicalReport : " + medicalRecordResult);
        logger.debug("Finish");
        return medicalRecordResult;

    }

    /*---------------------------  Delete -----------------------------*/

    @DeleteMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalRecord> deleteMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeDeletedException {

        logger.debug("Start");


        List<MedicalRecord> medicalRecordList = medicalRecordService.delete(medicalRecord);

        if ((medicalRecordList == null) || (medicalRecordList.size()==0)) {
            throw new MedicalRecordCanNotBeDeletedException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be deleted");
        }

        logger.info("Delete /medicalReport : " + medicalRecordList );
        logger.debug("Finish");
        return medicalRecordList;

    }

}
