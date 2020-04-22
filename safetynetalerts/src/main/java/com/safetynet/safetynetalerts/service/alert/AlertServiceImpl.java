package com.safetynet.safetynetalerts.service.alert;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Child;
import com.safetynet.safetynetalerts.model.detail.Childs;
import com.safetynet.safetynetalerts.model.detail.Phone;
import com.safetynet.safetynetalerts.service.person.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlertServiceImpl implements AlertService {

    private static final Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonDao personDao;
    @Autowired
    private FirestationDao firestationDao;
    @Autowired
    private MedicalRecordDao medicalRecordDao;
    /**
     * Get Child list by address
     * @param address
     * @return
     */
    @Override
    public Childs getChildByAddress (String address){

        Childs childsResult = new Childs();
        List<Child> childList = new ArrayList<>();
        List<Person> personList = new ArrayList<>();
        int age = 0;
        //List<Person> childListResult = new ArrayList<>();
        List<String> addressList = new ArrayList<>();
        addressList.add(address);
        //Person child = null;
        //Get all person at this address
        List<Person> personListAddress = personDao.getPersonByAdress(addressList);

        if ((personListAddress != null) && (!personListAddress.isEmpty())) {
            for (Person ePerson : personListAddress) {
                age = medicalRecordDao.getAgeByPerson(ePerson);
                if (age <= 18) {
                    childList.add(new Child(ePerson.getFirstName(), ePerson.getLastName(), String.valueOf(age)));
                } else {
                    personList.add(ePerson);
                }
            }
            childsResult.setChilds(childList);
            childsResult.setPersons(personList);
        }

        return childsResult;
    }
 /*   @Override
    public List<Person> getChildByAddress (String address){
        List<Person> childListResult = new ArrayList<>();
        List<String> addressList = new ArrayList<>();
        addressList.add(address);
        Person child = null;
        //Get all person at this address
        List<Person> personListAddress = personDao.getPersonByAdress(addressList);

            if ((personListAddress != null) && (!personListAddress.isEmpty())) {
                List<MedicalRecord> medicalRecordList = medicalRecordDao.getChildByPersonList(personListAddress);
                List<Person> childList = new ArrayList<>();
                for (MedicalRecord eMedicalRecord : medicalRecordList) {
                    child = new Person(eMedicalRecord.getFirstName(), eMedicalRecord.getLastName(), "", "", "", "", "");
                    childList.add(child);
                }
                childListResult = personDao.getPersonByName(childList);
            }

        return childListResult;
    }*/

    /**
     * Get phone list by station
     * @param station
     * @return
     */
    @Override
    public List<Phone> getPhoneByStation (String station){
        List<Phone> phoneList = new ArrayList<>();
        List<String> addressList = new ArrayList<>();
        List<Firestation> firestationList = firestationDao.getFirestationListByStation(station);
        if ((firestationList != null) && (!firestationList.isEmpty())) {
            for (Firestation eFirestation: firestationList){
                addressList.add(eFirestation.getAddress());
            }
            if ((addressList != null) && (!addressList.isEmpty())) {
                Phone phone = null;
                List<Person> personListAddress = personDao.getPersonByAdress(addressList);
                if ((personListAddress != null) && (!personListAddress.isEmpty())) {
                    for (Person ePerson : personListAddress) {
                        phone = new Phone(ePerson.getFirstName(), ePerson.getLastName(), ePerson.getPhone());
                        phoneList.add(phone);
                    }
                }
            }
        }
        return phoneList;
    }
}
