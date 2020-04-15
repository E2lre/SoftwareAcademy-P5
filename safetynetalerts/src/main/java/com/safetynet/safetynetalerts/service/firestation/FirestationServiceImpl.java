package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationServiceImpl implements FirestationService {
    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(FirestationServiceImpl.class);

    @Autowired
    private FirestationDao firestationDao;

    @Override
    public List<Firestation> findAll() {
        return firestationDao.findAll();
    }

    @Override
    public Firestation findByStation (String station) {
        logger.debug("Start/finish");
        return firestationDao.findByStation(station);
    }

    @Override
    public Firestation save (Firestation firestation){
        logger.debug("Start/finish");
        return firestationDao.save(firestation);
    }

    @Override
    public Firestation updateByStation (Firestation firestation){
        logger.debug("Start/finish");
        return firestationDao.updateByStation(firestation);
    }
    @Override
    public Firestation updateByAddress(Firestation firestation){
        logger.debug("Start/finish");
        return firestationDao.updateByAddress(firestation);
    }
    @Override
    public List<Firestation> delete(Firestation firestation){
        logger.debug("Start/finish");
        return firestationDao.delete(firestation);
    }


    //   public List<FirestationDao> load (List<Firestation> firestationList);
}
