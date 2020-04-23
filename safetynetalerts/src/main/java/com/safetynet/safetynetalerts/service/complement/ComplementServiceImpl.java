package com.safetynet.safetynetalerts.service.complement;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Email;
import com.safetynet.safetynetalerts.model.detail.Fire;
import com.safetynet.safetynetalerts.model.detail.Flood;
import com.safetynet.safetynetalerts.model.detail.PersonInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComplementServiceImpl implements  ComplementService {
    private static final Logger logger = LogManager.getLogger(ComplementServiceImpl.class);
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private PersonDao personDao;
    @Autowired
    private MedicalRecordDao medicalRecordDao;
    /**
     * return Fire for an address
     * @param address address ask
     * @return fire
     */
     @Override
    public List<Fire> getFireByAddress (String address) {
        logger.debug("Start");
        List<Fire> fireListResult = new ArrayList<>();
        //get the station number
        Firestation firestation = firestationDao.findByAddress(address);

        List<Person> personList = new ArrayList<>();
        if (firestation!=null){
            //Get list of person at this adresss
            List<String> addressList = new ArrayList<>();
            addressList.add(address);
            personList = personDao.getPersonByAdress(addressList);
            if (personList!=null) {
                for(Person ePerson: personList){
                    //get medical info for each person
                    MedicalRecord medicalRecord = new MedicalRecord(ePerson.getFirstName(),ePerson.getLastName(),"",null,null);
                    MedicalRecord medicalRecordResult = medicalRecordDao.get(medicalRecord);
                    if (medicalRecordResult!=null) {
                        int age = medicalRecordDao.getAgeByPerson(ePerson);
                        Fire fire = new Fire(firestation.getStation(),ePerson.getFirstName(),ePerson.getLastName(),ePerson.getPhone(),String.valueOf(age),medicalRecordResult.getMedications(),medicalRecordResult.getAllergies());
                        fireListResult.add(fire);
                    }
                }
            }
        }
        logger.debug("Finish");
     return fireListResult;
    }

    /**
     * return flood list with a station list
     * @param stationList station Number list
     * @return flood list full or empty
     */
    @Override
    public List<Flood> getFloodByStationList (List<String> stationList){
        logger.debug("Start");
         List<Flood> floodListResult = new ArrayList<>();

         for(String eStation : stationList){
             // Find address List for station list
             List<Firestation> firestationList = firestationDao.getFirestationListByStation(eStation);
             if (!firestationList.isEmpty()){
                 //for each address, find the fire list
                 for (Firestation eFirestation : firestationList){
                     List<Fire> fireList = this.getFireByAddress(eFirestation.getAddress());
                     //load output list
                     if (!fireList.isEmpty()){
                         floodListResult.add(new Flood(eFirestation.getAddress(),fireList));
                     }
                 }
             }
        }


        logger.debug("Finish");
         return floodListResult;
    }

    /**
     * Get a list of PersonInfo by firstname and lastname or lastname
     * @param firstName of person
     * @param lastName of person
     * @return a list of PersonInfo
     */
    @Override
    public List<PersonInfo> getPersonInfoByFisrtNameLastName (String firstName, String lastName){
        logger.debug("Start");
        List<PersonInfo> personInfoList = new ArrayList<>();
        List<Person> personInput = new ArrayList<>();
        personInput.add(new Person(firstName,lastName,"","","","",""));
        List<Person>  personList = personDao.getPersonByName(personInput);
        for (Person ePerson:personList) {
            MedicalRecord medicalRecordInput = new MedicalRecord(ePerson.getFirstName(),ePerson.getLastName(),"",null,null);
            MedicalRecord medicalRecordResult = medicalRecordDao.get(medicalRecordInput);
            PersonInfo personInfo = new PersonInfo(ePerson.getFirstName(),ePerson.getLastName(),ePerson.getAddress(),String.valueOf(medicalRecordDao.getAgeByPerson(ePerson)),ePerson.getEmail(),medicalRecordResult.getMedications(),medicalRecordResult.getAllergies());
            personInfoList.add(personInfo);
        }
        logger.debug("Finish");
        return personInfoList;
    }

    /**
     * get email on person of city
     * @param city to analyse
     * @return list  email of person of city
     */
    @Override
    public List<Email> getEmailByCity (String city){
        logger.debug("Start");
        List<Email> emailList = new ArrayList<>();

        List<Person> personList = personDao.getPersonByCity(city);
        if (!personList.isEmpty()) {
            for (Person ePerson : personList) {
                emailList.add(new Email(ePerson.getEmail()));
            }
        }
        logger.debug("Finish");
        return emailList;
    }
}
