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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


/*@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc*/
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
    //@Autowired
    private Firestation fireststationTest;
    @MockBean
    private Firestation firestation;
    //@MockBean
    //public static List<Firestation> firestations;
//    = new ArrayList<>();
    @MockBean
    private InputDataReader inputDataReader;

    String correctAddress = "748 Townings Dr";
    String correctStation = "3";
    String incorrectAddress = "Incorrect Address";
    String incorrectStation ="99";
    List<Firestation>  deletetdFirestations;

    @BeforeEach
    private void setUpPerTest() {

/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
*/
        fireststationTest = new Firestation();
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
/*
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
*/
        deletetdFirestations= new ArrayList<>();
    }


    @AfterEach
    private void cleanUpEach() {
    //    firestationDao = null;
    //    fireststationTest = null;
//        if (!deletetdFirestations.isEmpty()) {
//            firestationDao. .removeAll(Firestation);
//        }
        firestationDao.clear();
    }

    /*----------------------- get ---------------------------*/
    @Test
    public void findByStation_aStationExist_theStationIsReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.findByStation("3");

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }
    @Test
    public void findByStation_aStationNotExist_nullIsReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.findByStation("5");

        //THEN
        assertThat(fireststationTest).isNull();
    }
    @Test
    public void findAll_twoStationAdd_twoStationMoreAreReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
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
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        //Firestation fireststationTest = new Firestation();
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
        //TODO ajouter controle de la liste
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
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        //WHEN
        //Firestation fireststationTest = new Firestation();
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
        //Firestation fireststationTest = new Firestation();
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
        //Firestation fireststationTest = new Firestation();
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
        //Firestation fireststationTest = new Firestation();
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
        //Firestation fireststationTest = new Firestation();
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

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(correctAddress, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }

    @Test
    public void delete_byExistingAddress_severalfirestationdeletedIsReturn() {
        //GIVEN

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(correctAddress, incorrectStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(correctAddress, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(2);
    }

    @Test
    public void delete_byInexistingAddress_firestationListdeletedIsReturn() {
        //GIVEN

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        //fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
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

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(null, correctStation);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }
    @Test
    public void delete_byExistingStation_severalfirestationdeletedIsReturn() {
        //GIVEN

   //     List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(null, correctStation);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(2);
    }
    @Test
    public void delete_byInexistingStation_firestationListdeletedIsReturn() {
        //GIVEN

   //     List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        //fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
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

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(correctAddress, correctStation);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(1);
    }
    @Test
    public void delete_byInexistingStationAndAddress_firestationListdeletedIsReturn() {
        //GIVEN

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        //fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
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

    //    List<Firestation>  deletetdFirestations= new ArrayList<>();
 /*       firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
 */
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        //fireststationTest = firestationDao.save(new Firestation(incorrectAddress, incorrectStation));
 /*       String incorrectStation = "98";
        String newAddress = "new Address";
        Firestation fireStationToUpdate = new Firestation(newAddress, incorrectStation);*/
        Firestation fireStationToDelete = new Firestation(null, null);
        //WHEN
        deletetdFirestations = firestationDao.delete(fireStationToDelete);

        //THEN
        assertThat(deletetdFirestations.size()).isEqualTo(0);
        assertThat(deletetdFirestations).isEmpty();
    }
}
