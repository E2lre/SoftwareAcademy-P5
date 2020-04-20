package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.MedicalRecords;
import com.safetynet.safetynetalerts.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public class MedicalRecordDaoImpl implements MedicalRecordDao  {
    private static final Logger logger = LogManager.getLogger(PersonDaoImpl.class);

    private static List<MedicalRecord> medicalRecords = new ArrayList<>();;

    /**
     * Get a medicalRecord
     * @param medicalRecord firstname and lastname to find
     * @return the medicalRecord ask, null if not found
     */
    @Override
    public MedicalRecord get (MedicalRecord medicalRecord) {

        logger.debug("start");

        MedicalRecord  resultMedicalRecord = null;

        for(MedicalRecord eMedicalRecord : medicalRecords){
            if ((eMedicalRecord.getFirstName().equals(medicalRecord.getFirstName()))&&(eMedicalRecord.getLastName().equals(medicalRecord.getLastName()))){
                resultMedicalRecord = eMedicalRecord;
            }
        }
        logger.debug("Finish");
        return resultMedicalRecord;

    }
    /**
     * Add a medicalRecord
     * @param medicalRecord to add
     * @return medicalRecord add, null if already exist
     */
    @Override
    public MedicalRecord add (MedicalRecord medicalRecord) {

        logger.debug("start");
        MedicalRecord  resultMedicalRecord = null;
        boolean medicalRecordExist = false;

        //Detect if the medicalrecord already exist
        for(MedicalRecord eMedicalRecord : medicalRecords){
            if ((eMedicalRecord.getFirstName().equals(medicalRecord.getFirstName()))&&(eMedicalRecord.getLastName().equals(medicalRecord.getLastName()))){
                medicalRecordExist = true;
            }
        }
        //If teh person doesn't exist, creat person
        if (!medicalRecordExist) {
            medicalRecords.add(medicalRecord) ;
            resultMedicalRecord = medicalRecord;
        } else {
            logger.debug("Person already exist : Firstname :" + medicalRecord.getFirstName()+" Lastname :" + medicalRecord.getLastName());
        }


        logger.debug("Finish");
        return resultMedicalRecord;
    }

    /**
     * update an existing medicalRecord (Firstname + Lastname is the key)
     * @param medicalRecord to update
     * @return medicalRecord updated, null if the person doesn't exist
     */
    @Override
    public MedicalRecord update (MedicalRecord medicalRecord) {

        logger.debug("start");
        MedicalRecord  resultMedicalRecord = null;


        //Detect if the person already exist
        for(MedicalRecord eMedicalRecord : medicalRecords){
            if ((eMedicalRecord.getFirstName().equals(medicalRecord.getFirstName()))&&(eMedicalRecord.getLastName().equals(medicalRecord.getLastName()))){
                int position = medicalRecords.indexOf(eMedicalRecord); //If the person exist, find the position in the list in memory and update
                if (position <0) {
                    resultMedicalRecord = null;
                } else {
                    medicalRecords.set(position,medicalRecord);
                    resultMedicalRecord = medicalRecord;
                }
            }
        }

        logger.debug("Finish");
        return resultMedicalRecord;
    }

    /**
     * delete an existing medicalRecord
     * @param medicalRecord person to delete (firstname + lastname is the key
     * @return medicalRecord delete, else null
     */
    @Override
    public List<MedicalRecord> delete (MedicalRecord medicalRecord) {

        logger.debug("start");
        List<MedicalRecord>  resultMedicalRecordList = new ArrayList<>();


        //Detect if the person already exist
/*
        for(MedicalRecord eMedicalRecord : medicalRecords){
            if ((eMedicalRecord.getFirstName().equals(medicalRecord.getFirstName()))&&(eMedicalRecord.getLastName().equals(medicalRecord.getLastName()))){
                int position = medicalRecords.indexOf(eMedicalRecord); //If the person exist, find the position in the list in memory and delete
                if (position >=0)  {
                    resultMedicalRecordList.add(eMedicalRecord);
                    medicalRecords.remove(position);
                }
            }
        }
*/

        Iterator itr = medicalRecords.iterator();

        while (itr.hasNext()){
            MedicalRecord indexMedicaRecord = (MedicalRecord) itr.next();
            //Detect if the medicaj record already exist
            if ((indexMedicaRecord.getFirstName().equals(medicalRecord.getFirstName())) && (indexMedicaRecord.getLastName().equals(medicalRecord.getLastName()))){
                resultMedicalRecordList.add(indexMedicaRecord);
                itr.remove();
            }
        }
        logger.debug("Finish");
        return resultMedicalRecordList;
    }
    @Override
    public boolean load (List<MedicalRecord> medicalRecordList) {
        medicalRecords.addAll(medicalRecordList);
        return true;
    }
    /**
     * clear the list of medicalRecords in mémory
     * use by unit test
     * @return true if ok (always)
     */
    @Override
    public boolean clear(){
        medicalRecords.clear();
        return true;
    }

    /**
     * give a list of medical of child in a list of person
     * @param personList of personwhere child must be found
     * @return medical record only with firstname lastname ans birthdate
     */
    @Override
    public List<MedicalRecord> getChildByPersonList (List<Person> personList) { //throws ParseException {
        List<MedicalRecord> medicalRecordListResult = new ArrayList<>();
        //Date date = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate today = LocalDate.now();

        try {
            for (Person ePerson : personList) {
                for (MedicalRecord eMedicalRecod : medicalRecords) {
                    if ((ePerson.getFirstName().equals(eMedicalRecod.getFirstName())) && (ePerson.getLastName().equals(eMedicalRecod.getLastName()))) {

                        LocalDate birthdate = LocalDate.parse(eMedicalRecod.getBirthdate(), formatter);

                        Period period = Period.between(birthdate, today);

                        if (period.getYears() < 18) {
                            medicalRecordListResult.add(eMedicalRecod);
                        }
                    }
                }
            }
            return medicalRecordListResult;
        }catch(Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }
}
