package com.safetynet.safetynetalerts.web.controller;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.medicalrecord.MedicalRecordService;
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

    /**
     * get a medicalRecord by firstname and lastname
     * @param medicalRecord firstname and lastname mandatory in a medical record
     * @return the medical record
     * @throws MedicalRecordCanNotBeFoundException in case of error
     */
    @GetMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.OK)
    public MedicalRecord getMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeFoundException {


        logger.info("GET/medicalReport=" + medicalRecord);

        MedicalRecord medicalRecordResult = medicalRecordService.get(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotBeFoundException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be added");
        }

        logger.info("GET /medicalReport : " + medicalRecordResult);

        return medicalRecordResult;

    }
    /*---------------------------  Post -----------------------------*/

    /**
     * Add a medical record in memory
     * @param medicalRecord to be add
     * @return medical record added
     * @throws MedicalRecordCanNotbeAddedException in case of error
     */
    @PostMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.CREATED)
    public MedicalRecord addMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotbeAddedException {


        logger.info("POST/medicalReport=" + medicalRecord);

        MedicalRecord medicalRecordResult = medicalRecordService.add(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotbeAddedException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be added");
        }

        logger.info("POST /medicalReport : " + medicalRecordResult);

        return medicalRecordResult;

    }
    /*---------------------------  Put -----------------------------*/

    /**
     * update a medical record
     * @param medicalRecord new value of medical record
     * @return medical record added
     * @throws MedicalRecordCanNotBeModifyException in case of error
     */
    @PutMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public MedicalRecord modifyMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeModifyException {


        logger.info("PUT/medicalReport=" + medicalRecord);

        MedicalRecord medicalRecordResult = medicalRecordService.update(medicalRecord);

        if (medicalRecordResult == null) {
            throw new MedicalRecordCanNotBeModifyException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be modified");
        }

        logger.info("PUT /medicalReport : " + medicalRecordResult);

        return medicalRecordResult;

    }

    /*---------------------------  Delete -----------------------------*/

    /**
     * delate a medical record
     * @param medicalRecord medical record to be deleted
     * @return medical record deleted
     * @throws MedicalRecordCanNotBeDeletedException in case of error
     */
    @DeleteMapping(value="/medicalReport")
    @ResponseStatus(HttpStatus.OK)
    public List<MedicalRecord> deleteMedicalRecord(@Valid @RequestBody MedicalRecord medicalRecord) throws MedicalRecordCanNotBeDeletedException {

        logger.info("Delete/medicalReport=" + medicalRecord );

        List<MedicalRecord> medicalRecordList = medicalRecordService.delete(medicalRecord);

        if ((medicalRecordList == null) || (medicalRecordList.size()==0)) {
            throw new MedicalRecordCanNotBeDeletedException("FirstName " + medicalRecord.getFirstName() + " lastname " + medicalRecord.getLastName() +" Can not be deleted");
        }

        logger.info("Delete /medicalReport : " + medicalRecordList );

        return medicalRecordList;

    }

}
