package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.dao.FirestationDaoNew;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import com.safetynet.safetynetalerts.service.firestation.FirestationServiceImpl;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class InputDataReaderImpl implements InputDataReader {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

    @Autowired
    FirestationService resultFirestationService;
    @Autowired
    FirestationDaoNew fsDao;

    @Override
    public List<Firestation> readInputDataXX() {
        logger.trace("Start");
        // read json
        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("mapper");

            List<FirestationDaoNew> firestations = mapper.reader()
                    .forType(new TypeReference<List<Firestation>>(){})
                    .readValue(new File("data.json"));
            List<Firestation> firestation = mapper.reader()
                    .forType(new TypeReference<List<Firestation>>(){})
                    .readValue(new File("data.json"));

            //   FirestationDao firestations = mapper.reader()
            //                                              .forType(FirestationDao.class)
            //                                              .readValue(new File("data.json"));

            logger.debug(firestations);
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
   public List<FirestationDaoNew> readInputData() {
        logger.trace("Start");
        // read json
        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("mapper");

            List<FirestationDaoNew> firestations = mapper.reader()
                                                    .forType(new TypeReference<List<Firestation>>(){})
                                                    .readValue(new File("data.json"));
            List<Firestation> firestation = mapper.reader()
                    .forType(new TypeReference<List<Firestation>>(){})
                    .readValue(new File("data.json"));

            //   FirestationDao firestations = mapper.reader()
         //                                              .forType(FirestationDao.class)
         //                                              .readValue(new File("data.json"));

            logger.debug(firestations);
            logger.debug(firestation);

            logger.trace("Finish");
            return firestations;
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

    @Override
    public FirestationService loadFirestation(List<FirestationDaoNew> firestationDaoList){
        logger.trace("Start");
        //FirestationService resultFirestationService = new FirestationServiceImpl();
    //    for(FirestationDaoNew eFirestation : listFirestation) {

        //for(FirestationDaoNew eFirestation : listFirestation) {
  /*      for(FirestationDaoNew eFirestation : listFirestationDao) {

            resultFirestationService.save(eFirestation);
            }*/
        resultFirestationService.load (firestationDaoList);
        logger.debug(resultFirestationService);
        logger.trace("Finish");
        return resultFirestationService;
    }
}

