package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.Firestation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FirestationDaoImpl implements FirestationDao {

    public static List<Firestation> firestations = new ArrayList<>();
    //TODO : a retirer quand la lecture du fichier de data sera opérationnelle DEBUT
 //   static {
       // firestations.add(new Firestation(new String ("1509 Culver St"), 3));
        //firestations.add(new Firestation(new String ("29 15th St"), 2));
        //firestations.add(new Firestation(new String ("834 Binoc Ave"), 3));
  //  }

    //TODO : a retirer quand la lecture du fichier de data sera opérationnelle FIN

    @Override
    public List<Firestation> findAll() {
        return firestations;
    }

    @Override
    //public Firestation finByStation(int station) {
    public Firestation findByStation(String station) {
        Firestation localFirestation = null;
        for(Firestation firestation : firestations){
            if (firestation.getStation().equals(station)){
                localFirestation = firestation;
            }
        }
        return localFirestation;
    }

    @Override
    public Firestation save(Firestation firestation) {
        firestations.add(firestation);
        return firestation;
    }


}
