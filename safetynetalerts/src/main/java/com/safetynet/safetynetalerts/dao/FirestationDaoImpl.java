package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FirestationDaoImpl implements FirestationDao {

    //private static final Logger logger = Logger.getLogger(SafetyalertsController.class);
    private static final Logger logger = LogManager.getLogger(FirestationDaoImpl.class);

    public static List<Firestation> firestations = new ArrayList<>();

    @Override
    public List<Firestation> findAll() {
        return firestations;
    }

    @Override

    public Firestation findByStation(String station) {
        logger.debug("start");
        Firestation localFirestation = null;
        for (Firestation firestation : firestations) {
            if (firestation.getStation().equals(station)) {
                localFirestation = firestation;
            }
        }
        logger.debug("Finish");
        return localFirestation;
    }

    @Override
    public Firestation findByAddress(String address) {
        logger.debug("start");
        Firestation localFirestation = null;
        for (Firestation firestation : firestations) {
            if (firestation.getAddress().equals(address)) {
                localFirestation = firestation;
            }
        }
        logger.debug("Finish");
        return localFirestation;
    }

    @Override
    public Firestation save(Firestation firestation) {
        logger.debug("start");
        firestations.add(firestation);
        //TODO : ajouter Déjà existe
        logger.debug("Finish");
        return firestation;
    }

    /**
     * update a station by a number station. only address will be updated. Number station is unchange
     *
     * @param firestation
     * @return firestation if OK, null else
     */
    @Override
    public Firestation updateByStation(Firestation firestation) {
        logger.debug("start");
        Firestation localFirestation = findByStation(firestation.getStation()); //find the station with right number station
        if (localFirestation != null) {
            int position = firestations.indexOf(localFirestation);
            if (position < 0) {    //station not found
                firestation = null;
            } else {
                firestations.set(position, firestation);
            }
        } else {
            firestation = null;
        }
        logger.debug("Finish");
        return firestation;
    }

    @Override
    public Firestation updateByAddress(Firestation firestation) {
        logger.debug("start");

        Firestation localFirestation = findByAddress(firestation.getAddress()); //find the station with right number station
        if (localFirestation != null) {
            int position = firestations.indexOf(localFirestation);
            if (position < 0) {    //station not found
                firestation = null;
            } else {
                firestations.set(position, firestation);
            }
        } else {
            firestation = null;
        }
        logger.debug("Finish");
        return firestation;
    }

    /**
     * Delete a station or an adress
     *
     * @param firestation to delete, complete or only address or only station
     * @return list of firestation deleted
     */
    @Override
    public List<Firestation> delete(Firestation firestation) {
        logger.debug("start");
        List<Firestation> DeletedFirestation = new ArrayList<>();
        if ((firestation.getAddress() != null) && (firestation.getStation() == null)) { //Delete by address
            Iterator itr = firestations.iterator();
            while (itr.hasNext()) {
                Firestation indexFirestation = (Firestation) itr.next();
                if (indexFirestation.getAddress().equals(firestation.getAddress())) {
                    DeletedFirestation.add(indexFirestation);
                    itr.remove();
                }
            }
        } else {
            if ((firestation.getAddress() == null) && (firestation.getStation() != null)) { //Delete by station
                Iterator itr = firestations.iterator();
                while (itr.hasNext()) {
                    Firestation indexFirestation = (Firestation) itr.next();
                    if (indexFirestation.getStation().equals(firestation.getStation())) {
                        DeletedFirestation.add(indexFirestation);
                        itr.remove();
                    }
                }
            } else {
                if ((firestation.getAddress() != null) && (firestation.getStation() != null)) { //Delete by adress & station
                    Iterator itr = firestations.iterator();
                    while (itr.hasNext()) {
                        Firestation indexFirestation = (Firestation) itr.next();
                        //TODO : tester en comparant les firestation au lieu des get
                        //                      if ((indexFirestation.getStation()==firestation.getStation() && (indexFirestation.getAddress()==firestation.getAddress()))){
                        if ((indexFirestation.getStation().equals(firestation.getStation())) && (indexFirestation.getAddress().equals(firestation.getAddress()))) {
                            DeletedFirestation.add(indexFirestation);
                            itr.remove();
                        }
                    }
                } else { // Station and address are null
                    logger.error("impossible to delete firestation because station is null and adress is null");
                }
            }
        }
        logger.debug("Finish");
        return DeletedFirestation;
    }

    /**
     * clear the list off firestation in mémory
     * use by unit test
     *
     * @return true if ok (always)
     */
    @Override
    public boolean clear() {
        firestations.clear();
        return true;
    }

    @Override
    public List<Firestation> getFirestationListByStation(String station) {
        logger.debug("start");
        List<Firestation> localFirestationList = new ArrayList<>();
        for (Firestation eFirestation : firestations) {
            if (eFirestation.getStation().equals(station)) {
                localFirestationList.add(eFirestation);
            }
        }
        logger.debug("Finish");
        return localFirestationList;
    }
}