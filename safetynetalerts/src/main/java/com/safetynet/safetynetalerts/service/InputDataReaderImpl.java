package com.safetynet.safetynetalerts.service;


import com.jsoniter.JsonIterator;
import com.safetynet.safetynetalerts.dao.*;

import com.safetynet.safetynetalerts.model.*;

import com.safetynet.safetynetalerts.model.load.ApplicationData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Repository
public class InputDataReaderImpl implements InputDataReader {

    private static final Logger logger = LogManager.getLogger(InputDataReaderImpl.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private  PersonDao personDao;
    @Autowired
    private MedicalRecordDao medicalRecordDao;

    /**
     * Read input data.json file to memory
     * @return ture if OK
     */
    @Override
    public boolean readInputFile() {
        try {
            logger.debug("Start");
            boolean resultat = false;
            boolean resultatFirestation = false;
            boolean resultatPerson = false;
            boolean resultatMedicalRecord = false;
            String pathFile = "data.json";

            String data = StreamUtils.copyToString(new ClassPathResource(pathFile).getInputStream(), Charset.defaultCharset());


            ApplicationData applicationData = JsonIterator.deserialize(data,ApplicationData.class);
            logger.debug("Data Loaded from file");


            //Get firestation in applicationData
            List<Firestation> listFirestation = applicationData.getFirestations();

            //Load FirestationSationDao
            resultatFirestation = firestationDao.load(listFirestation);
            if (resultatFirestation){
                logger.debug("Firestation List Load in memory");
            } else{
                logger.error("Error for loading Firestation List in memory");
            }

            //Get person in applicationData
            List<Person> personList = applicationData.getPersons();
            //Load personDao
            resultatPerson = personDao.load(personList);
            if (resultatPerson) {
                logger.debug("Person List Load in memory");
            } else{
                logger.error("Error for loading Person List in memory");
            }

            //Get medicalRecord in applicationData
            List<MedicalRecord> medicalRecordList = applicationData.getMedicalrecords();
            //Load medicalRecordDao
            resultatMedicalRecord =medicalRecordDao.load(medicalRecordList);
            if (resultatMedicalRecord) {
                logger.debug("Medical Record List Load in memory");
            } else{
                logger.error("Error for loading Medical Record  List in memory");
            }

            resultat = resultatFirestation&&resultatPerson&&resultatMedicalRecord;
            logger.debug("Finish");
            return resultat;
        }
        catch (IOException e){
            logger.error(e);
            return false;
        }
    }

}

