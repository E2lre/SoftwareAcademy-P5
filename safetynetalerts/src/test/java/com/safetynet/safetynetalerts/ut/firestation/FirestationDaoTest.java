package com.safetynet.safetynetalerts.ut.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.InputDataReader;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class FirestationDaoTest {
    @TestConfiguration
    static class FirestationDaoImplTestContextConfiguration {
        @Bean
        public FirestationDao firestationDao () {
            return new FirestationDaoImpl();
        }
    }

    @Autowired
    private FirestationDao firestationDao;


    private Firestation fireststationTest;
    @MockBean
    private Firestation firestation;

    @MockBean
    private InputDataReader inputDataReader;

    String correctAddress = "748 Townings Dr";
    String correctStation = "3";
    String incorrectAddress = "Incorrect Address";
    String incorrectStation ="99";
    List<Firestation>  deletetdFirestations;

    @BeforeEach
    private void setUpPerTest() {
        firestationDao.clear();

        fireststationTest = new Firestation();
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");

        deletetdFirestations= new ArrayList<>();
    }


    @AfterEach
    private void cleanUpEach() {

        firestationDao.clear();
    }

    /*----------------------- get ---------------------------*/
    @Test
    public void findByStation_aStationExist_theStationIsReturn() {

        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));

        //WHEN
        fireststationTest = firestationDao.findByStation("3");

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }
    @Test
    public void findByStation_aStationNotExist_nullIsReturn() {

        //GIVEN
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        fireststationTest = firestationDao.findByStation("5");

        //THEN
        assertThat(fireststationTest).isNull();
    }
    @Test
    public void findAll_twoStationAdd_twoStationMoreAreReturn() {

        //GIVEN
        List<Firestation>  fireststations = firestationDao.findAll();
        int nbFirestationStart = fireststations.size();
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        fireststations = firestationDao.findAll();
        int nbFirestationFinal = fireststations.size();

        //THEN
        assertThat(nbFirestationFinal-nbFirestationStart).isEqualTo(2) ;
    }

    @Test
    public void findByAddress_aAddressExist_theFirestationIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        fireststationTest = firestationDao.findByAddress(correctAddress);

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }
    @Test
    public void findByAddress_aAddressNotExist_nullIsReturn() {

        //GIVEN
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        fireststationTest = firestationDao.findByAddress("Incorrect Address");

        //THEN
        assertThat(fireststationTest).isNull();
    }

    @Test
    public void getFirestationListByStation_stationExist_listOfFirestationReturn(){
        //GIVEN
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(incorrectAddress,incorrectStation));


        //WHEN
        List<Firestation> firestationList= firestationDao.getFirestationListByStation(correctStation);

        //THEN
        assertThat(firestationList.size()).isEqualTo(1);

    }

    @Test
    public void getFirestationListByStation_stationNotExist_nullIsReturn(){
        //GIVEN
        firestationDao.save(new Firestation(correctAddress, correctStation));

        //WHEN
        List<Firestation> firestationList= firestationDao.getFirestationListByStation(incorrectStation);

        //THEN
        assertThat(firestationList).isEmpty();
    }
    /*----------------------- add ---------------------------*/
    @Test
    public void save_oneStationToAdd_theStationAddedIsReturn() {

        //GIVEN

        //WHEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));

        //THEN
        List<Firestation>  fireststations = firestationDao.findAll();
        assertThat(fireststations.size()).isEqualTo(1) ;
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }
    /*----------------------- update ---------------------------*/
    @Test
    public void updateByAddress_addressExist_theFirestationIsUpdated() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
        String newStation = "99";
        Firestation fireStationToUpdate = new Firestation(correctAddress, newStation);

        //WHEN
        fireststationTest = firestationDao.updateByAddress(fireStationToUpdate);

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(newStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);
    }

    @Test
    public void updateByAddress_addressNotExist_nullIsReturn() {
        //GIVEN

        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
        String newStation = "99";
        String incorrectAdress = "Error Street";
        Firestation fireStationToUpdate = new Firestation(incorrectAdress, newStation);

        //WHEN
        fireststationTest = firestationDao.updateByAddress(fireStationToUpdate);

        //THEN
        assertThat(fireststationTest).isNull();
    }

    /**
     *
     */
    @Test
    public void updateByStation_stationExist_theFirestationIsUpdated() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, correctStation);

        //WHEN
        fireststationTest = firestationDao.updateByStation(fireStationToUpdate);

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(newAddress);
    }

    @Test
    public void updateBystation_stationNotExist_nullIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
        String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);

        //WHEN
        fireststationTest = firestationDao.updateByStation(fireStationToUpdate);

        //THEN
        assertThat(fireststationTest).isNull();
    }
    /*----------------------- delete ---------------------------*/
    @Test
    public void delete_byExistingAddress_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));

        Firestation fireStationToDelete = new Firestation(correctAddress, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }

    @Test
    public void delete_byExistingAddress_severalfirestationdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(correctAddress, incorrectStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));

        Firestation fireStationToDelete = new Firestation(correctAddress, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(2);
    }

    @Test
    public void delete_byInexistingAddress_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));

        Firestation fireStationToDelete = new Firestation(incorrectAddress, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(0);
        assertThat(deletetdFirestations).isEmpty();
    }
    @Test
    public void delete_byExistingStation_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));

        Firestation fireStationToDelete = new Firestation(null, correctStation);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }
    @Test
    public void delete_byExistingStation_severalfirestationdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));

        Firestation fireStationToDelete = new Firestation(null, correctStation);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(2);
    }
    @Test
    public void delete_byInexistingStation_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        Firestation fireStationToDelete = new Firestation(null, incorrectStation);

        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(0);
        assertThat(deletetdFirestations).isEmpty();
    }

    @Test
    public void delete_byExistingStationAndAddress_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
        Firestation fireStationToDelete = new Firestation(correctAddress, correctStation);

        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }
    @Test
    public void delete_byInexistingStationAndAddress_firestationListdeletedIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        Firestation fireStationToDelete = new Firestation(incorrectAddress, incorrectStation);

        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(0);
        assertThat(deletetdFirestations).isEmpty();
    }

    @Test
    public void delete_nullStationAndAddress_emptyListfirestationIsReturn() {
        //GIVEN
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        Firestation fireStationToDelete = new Firestation(null, null);

        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(0);
        assertThat(deletetdFirestations).isEmpty();
    }
}
