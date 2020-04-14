package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.*;

import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InputDataReaderImpl implements InputDataReader {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

/*
    @Autowired
    FirestationService resultFirestationService;
*/
 /*   @Autowired
    FirestationDaoNew fsDao;
*/
    //TODO à supprimer
    @Override
    public List<Firestation> readInputDataXX() {
        logger.trace("Start");
        // read json
        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("mapper");

/*
            List<FirestationDaoNew> firestations = mapper.reader()
                    .forType(new TypeReference<List<Firestation>>(){})
                    .readValue(new File("data.json"));
*/
            List<Firestation> firestation = mapper.reader()
                    .forType(new TypeReference<List<Firestation>>(){})
                   // .readValue(new File("data.json"));
                    .readValue(new File("data - firestation.json"));

            //   FirestationDao firestations = mapper.reader()
            //                                              .forType(FirestationDao.class)
            //                                              .readValue(new File("data.json"));

//            logger.debug(firestations);
            logger.debug(firestation);

            logger.trace("Finish");
            return firestation;
        }
        catch (Exception e){
            logger.error(e.getMessage());
            logger.trace("Finish with error");
            return null;
        }
//            TypeReference<List<User>> typeReference = new TypeReference<List<User>>(){};
//           InputStream inputStream = TypeReference.class.getResourceAsStream("data.json");
//            List<User> users = mapper.readValue(inputStream,typeReference);
    }
    //TODO à supprimer
    @Override
    public FirestationDao  loadFirestationXX(List<Firestation> listFirestation){
        logger.trace("Start");
        FirestationDao resultFirestationDao = new FirestationDaoImpl();
        for(Firestation eFirestation : listFirestation) {
            resultFirestationDao.save(eFirestation);
        }
        logger.debug(resultFirestationDao);
        logger.trace("Finish");
        return resultFirestationDao;
    }

    @Override
    public FirestationDao readInputFile() {
        try {
            logger.debug("Start");

            String pathFile = "data.json";

            String data = StreamUtils.copyToString(new ClassPathResource(pathFile).getInputStream(), Charset.defaultCharset());


            Firestations firestationList = JsonIterator.deserialize(data, Firestations.class);

            FirestationDao resultFirestationDao = new FirestationDaoImpl();
            List<Firestation> listFirestation = firestationList.getFirestations();
            for(Firestation eFirestation : listFirestation) {
                resultFirestationDao.save(eFirestation);
            }


            Persons personList = JsonIterator.deserialize(data, Persons.class);
//            PersonDao personDao = JsonIterator.deserialize(data, PersonDao.class);
            MedicalRecords medicalRecordList = JsonIterator.deserialize(data, MedicalRecords.class);

            logger.debug("Finish");
            return resultFirestationDao;
        }
        catch (IOException e){
            logger.error(e);
            return null;
        }
    }

}

