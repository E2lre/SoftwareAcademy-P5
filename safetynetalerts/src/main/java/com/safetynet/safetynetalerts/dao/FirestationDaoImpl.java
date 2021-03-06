package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class FirestationDaoImpl implements FirestationDao {

    private static final Logger logger = LogManager.getLogger(FirestationDaoImpl.class);

    private static List<Firestation> firestations = new ArrayList<>();

    /**
     * get the list of all the firestation in memory
     * @return list of firestation
     */
    @Override
    public List<Firestation> findAll() {
        return firestations;
    }

    /**
     * get a firestation by a station number
     * @param station number station to be find
     * @return a firestation
     */
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

    /**
     * get a firestation by adress
     * @param address of the station to be find
     * @return a firestation
     */
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

    /**
     * save a firestation
     * @param firestation to save
     * @return firestation saved
     */
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
     * @param firestation to be update
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

    /**
     * update a firestation by address
     * @param firestation the firestation to update
     * @return the firestation updated
     */
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
     * clear the list off firestation in memory
     * use by unit test
     *
     * @return true if ok (always)
     */
    @Override
    public boolean clear() {
        firestations.clear();
        return true;
    }
    /**
     * Load list of person at start application
     * @param firestationDaoList list of person to load
     * @return true if no errors
     */
    @Override
    public boolean load (List<Firestation> firestationDaoList) {
        logger.debug("Start");
        firestations.addAll(firestationDaoList);
        logger.debug(firestations);
        logger.debug("Finish");
        return true;
    }

    /**
     * Get a Firestation list by number station
     * @param station station number to be get
     * @return a list of firestation
     */
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