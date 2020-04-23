package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
//import org.apache.log4j.Logger;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.StationNumberInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationServiceImpl implements FirestationService {

    private static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MedicalRecordDao medicalRecordDao;
    @Override
    public List<Firestation> findAll() {
        return firestationDao.findAll();
    }

    @Override
    public Firestation findByStation(String station) {
        logger.debug("Start/finish");
        return firestationDao.findByStation(station);
    }

    @Override
    public Firestation save(Firestation firestation) {
        logger.debug("Start/finish");
        return firestationDao.save(firestation);
    }

    @Override
    public Firestation updateByStation(Firestation firestation) {
        logger.debug("Start/finish");
        return firestationDao.updateByStation(firestation);
    }

    @Override
    public Firestation updateByAddress(Firestation firestation) {
        logger.debug("Start/finish");
        return firestationDao.updateByAddress(firestation);
    }

    @Override
    public List<Firestation> delete(Firestation firestation) {
        logger.debug("Start/finish");
        return firestationDao.delete(firestation);
    }

    /**
     * get a StationNumberInfo (with person) by station Number
     * @param station a station number
     * @return a stationNumberInfo
     */
    @Override
    public StationNumberInfo getPersonByStation(String station) {
        logger.debug("Start");
        int nbAdulte=0;
        int nbChild=0;
        int age=0;
        StationNumberInfo stationNumberInfo = new StationNumberInfo();
        List<Firestation> firestationListResult = new ArrayList<>();
        List<Person> personListResult = new ArrayList<>();

        //recupérer les adresse associées à un numéro de station
        firestationListResult = firestationDao.getFirestationListByStation(station);

        //get adresses for the station
        List<String> adresses = new ArrayList<>();
        for (Firestation eFirestation : firestationListResult) {
            adresses.add(eFirestation.getAddress());
        }
        //Get persons at the address
        personListResult = personDao.getPersonByAdress(adresses);
        stationNumberInfo.setPersons(personListResult);
        for (Person ePerson: personListResult){
            age=medicalRecordDao.getAgeByPerson(ePerson);
            if (age<=18) {
                nbChild=nbChild+1;
            } else {
                nbAdulte=nbAdulte+1;
            }
        }
        stationNumberInfo.setPersons(personListResult);
        stationNumberInfo.setNbAdulte(String.valueOf(nbAdulte));
        stationNumberInfo.setNbChild(String.valueOf(nbChild));
        logger.debug("Finish");
        return stationNumberInfo;
    }
}