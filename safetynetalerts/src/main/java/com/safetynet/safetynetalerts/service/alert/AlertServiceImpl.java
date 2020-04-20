package com.safetynet.safetynetalerts.service.alert;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
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
    private MedicalRecordDao medicalRecordDao;
    /**
     * Get Child list bey address
     * @param address
     * @return
     */
    @Override
    public List<Person> getChildByAddress (String address){
        List<Person> childListResult = new ArrayList<>();
        List<String> addressList = new ArrayList<>();
        addressList.add(address);
        Person child = null;
        //Get all person at this address
        List<Person> personListAddress = personDao.getPersonByAdress(addressList);
  //     try {
            if ((personListAddress != null) && (!personListAddress.isEmpty())) {
                List<MedicalRecord> medicalRecordList = medicalRecordDao.getChildByPersonList(personListAddress);
                List<Person> childList = new ArrayList<>();
                for (MedicalRecord eMedicalRecord : medicalRecordList) {
                    child = new Person(eMedicalRecord.getFirstName(), eMedicalRecord.getLastName(), "", "", "", "", "");
                    childList.add(child);
                }
                childListResult = personDao.getPersonByName(childList);
            }
 /*       } catch (ParseException e){
            return null; //TODO logger correctement
        }*/
        return childListResult;
    }
}
