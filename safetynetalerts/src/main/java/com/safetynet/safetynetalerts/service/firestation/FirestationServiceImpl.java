package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationServiceImpl implements FirestationService {
    private static final Logger logger = Logger.getLogger(SafetyalertsController.class);

    @Autowired
    private FirestationDao firestationDao;

    @Override
    public List<Firestation> findAll() {
        return firestationDao.findAll();
    }

    @Override
    public Firestation findByStation (String station) {
        return firestationDao.findByStation(station);
    }

    @Override
    public Firestation save (Firestation firestation){
        logger.trace("Start/finish");
        return firestationDao.save(firestation);
    }

    @Override
    public Firestation updateByStation (Firestation firestation){
        logger.trace("Start/finish");
        return firestationDao.updateByStation(firestation);
    }
    @Override
    public Firestation updateByAddress(Firestation firestation){
        logger.trace("Start/finish");
        return firestationDao.updateByAddress(firestation);
    }
    @Override
    public List<Firestation> delete(Firestation firestation){
        logger.trace("Start/finish");
        return null;
    }


    //   public List<FirestationDao> load (List<Firestation> firestationList);
}
