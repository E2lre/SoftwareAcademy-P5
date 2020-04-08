package com.safetynet.safetynetalerts.service.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoNew;
import com.safetynet.safetynetalerts.model.Firestation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FirestationServiceNewImpl implements FirestationServiceNew {

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
        return firestationDao.save(firestation);
    }
 //   public List<FirestationDao> load (List<Firestation> firestationList);
}
