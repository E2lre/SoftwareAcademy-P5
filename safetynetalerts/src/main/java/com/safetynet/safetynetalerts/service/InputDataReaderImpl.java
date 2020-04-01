package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository
public class InputDataReaderImpl implements InputDataReader {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    @Override
   public List<Firestation> readInputData() {
        logger.trace("Start");
        // read json
        try {
            ObjectMapper mapper = new ObjectMapper();
            logger.trace("mapper");

            List<Firestation> firestations = mapper.reader()
                                                    .forType(new TypeReference<List<Firestation>>(){})
                                                    .readValue(new File("data.json"));

            //   FirestationDao firestations = mapper.reader()
         //                                              .forType(FirestationDao.class)
         //                                              .readValue(new File("data.json"));

            logger.debug(firestations);

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
    public FirestationDao loadFirestation(List<Firestation> listFirestation){
        logger.trace("Start");
        FirestationDao resultFirestationDao = new FirestationDaoImpl();
        for(Firestation eFirestation : listFirestation) {
                resultFirestationDao.save(eFirestation);
            }
        logger.debug(resultFirestationDao);
        logger.trace("Finish");
        return resultFirestationDao;
    }
}

