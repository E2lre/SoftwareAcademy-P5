package com.safetynet.safetynetalerts.service.medicalrecord;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.person.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicalRecordServiceImpl implements MedicalRecordService {
    private static final Logger logger = LogManager.getLogger(MedicalRecordServiceImpl.class);

    @Autowired
    private MedicalRecordDao medicalRecordDao;

    @Override
    public MedicalRecord get (MedicalRecord medicalRecord){
        logger.debug("Start/finish");
        return medicalRecordDao.get(medicalRecord);
    }
    @Override
    public MedicalRecord add (MedicalRecord medicalRecord){
        logger.debug("Start/finish");
        return medicalRecordDao.add(medicalRecord);
    }
    @Override
    public MedicalRecord update (MedicalRecord medicalRecord){
        logger.debug("Start/finish");
        return medicalRecordDao.update(medicalRecord);
    }
    @Override
    public List<MedicalRecord> delete (MedicalRecord medicalRecord){
        logger.debug("Start/finish");
        return medicalRecordDao.delete(medicalRecord);
    }
}
