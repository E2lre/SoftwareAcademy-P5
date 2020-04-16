package com.safetynet.safetynetalerts.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.safetynet.safetynetalerts.dao.*;

import com.safetynet.safetynetalerts.model.*;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@Repository
public class InputDataReaderImpl implements InputDataReader {

    private static final Logger logger = LogManager.getLogger(InputDataReaderImpl.class);

/*
    @Autowired
    FirestationService resultFirestationService;
*/
 /*   @Autowired
    FirestationDaoNew fsDao;
*/

/*    @Autowired
    PersonDao personDao;*/

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
//TODO revoir le chargement et le réécrire ==> Lundi aprem
    @Override
    public FirestationDao readInputFile() {
        try {
            logger.debug("Start");
            boolean resultat = false;
            String pathFile = "data.json";

            String data = StreamUtils.copyToString(new ClassPathResource(pathFile).getInputStream(), Charset.defaultCharset());

            Firestations firestationList = JsonIterator.deserialize(data, Firestations.class);
           // List<Firestation> firestationList = JsonIterator.deserialize(data, firestationList.class);

            FirestationDao resultFirestationDao = new FirestationDaoImpl();
            List<Firestation> listFirestation = firestationList.getFirestations();
            for(Firestation eFirestation : listFirestation) {
                resultFirestationDao.save(eFirestation);
            }

//TODO revoir tout cette gestion avec les new et les lists de dao
            //Persons personList = JsonIterator.deserialize(data, Persons.class);
            Persons persons = JsonIterator.deserialize(data, Persons.class);
            List<Person> personList = persons.getPersons();

            PersonDao personDao = new PersonDaoImpl() ;
            resultat = personDao.load(personList);

            //logger.debug(personList);
//            for(Person ePerson : personList){
//                personDao.add(ePerson);
//            }
            //PersonDao personList = JsonIterator.deserialize(data, PersonDaoImpl.class);
            //personDao= JsonIterator.deserialize(data, PersonDaoImpl.class);
            //List<Person> personList= JsonIterator.deserialize(data,new TypeLiteral<List<Person>>(){});
  //          personDao.load(personList);
            MedicalRecords medicalRecords = JsonIterator.deserialize(data, MedicalRecords.class);
            List<MedicalRecord> medicalRecordList = medicalRecords.getMedicalRecords();

            MedicalRecordDao medicalRecordDao = new MedicalRecordDaoImpl();
            resultat =medicalRecordDao.load(medicalRecordList);
            logger.debug("Finish");
            return resultFirestationDao;
        }
        catch (IOException e){
            logger.error(e);
            return null;
        }
    }

}

