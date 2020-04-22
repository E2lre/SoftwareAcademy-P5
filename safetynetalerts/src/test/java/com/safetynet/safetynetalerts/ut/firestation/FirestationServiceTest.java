package com.safetynet.safetynetalerts.ut.firestation;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.StationNumberInfo;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class FirestationServiceTest {

    private Firestation fireststationTest;
    //private Person person;
    @Autowired
    private FirestationService firestationService;
    @MockBean
    private FirestationDao firestationDao;
    @MockBean
    private PersonDao personDao;
    @MockBean
    private MedicalRecordDao medicalRecordDao;
    @MockBean
    private InputDataReader inputDataReader;

    String correctAddress;
    String correctStation;
    String incorrectAddress;
    String incorrectStation;


    @BeforeEach
    private void setUpPerTest() {
        correctAddress = "Correct Address";
        correctStation ="1";
        incorrectAddress = "Incorrect Address";
        incorrectStation ="99";
    }

    /*----------------------- get ---------------------------*/

    @Test
    public void getPersonByStation_aStationNumberGiven_listofPersonReturn() {
        //Given
        List<Firestation>  firestations = new ArrayList<>();
        firestations.add(new Firestation("adress1", "1"));
        firestations.add(new Firestation("adress2", "1"));

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("firstName1","lastName1","address1","city1","zip1","phone1","email1"));
        persons.add(new Person("firstName2","lastName2","address1","city1","zip1","phone2","email2"));

        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(firestations);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(persons);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(20);

        //WHEN
        StationNumberInfo stationNumberInfo = firestationService.getPersonByStation("1");
        //THEN
        assertThat(stationNumberInfo.getNbAdulte()).isEqualTo("2");
        assertThat(stationNumberInfo.getNbChild()).isEqualTo("0");
        //TODO : ajouter l'analyse de la liste
    }

    @Test
    public void getPersonByStation_anInexistingStationNumberGiven_nullIsReturn() {
        //GIVEN
        List<Firestation> firestationListMock = new ArrayList<>();
        List<Person> personListMock = new ArrayList<>();
        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(firestationListMock);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personListMock);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(0);
        //WHEN
        //WHEN
        StationNumberInfo stationNumberInfo = firestationService.getPersonByStation("1");
        //THEN
        assertThat(stationNumberInfo.getPersons()).isEmpty();

    }
    @Test
    public void findAll_twoStationAdd_twoStationMoreAreReturn() {
        //GIVEN
        List<Firestation>  firesstations = new ArrayList<>();
        firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
        Mockito.when(firestationDao.findAll()).thenReturn(firesstations);
        //WHEN
        List<Firestation> fireststatioList = firestationService.findAll();
        //THEN
        assertThat(fireststatioList.size()).isEqualTo(2);
    }


    @Test
    public void findByStation_aStationExist_theStationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.findByStation(correctStation);
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void findByStation_aStationNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.findByStation(incorrectStation);
        //THEN
        assertThat(fireststationTest).isNull();

    }
    /*----------------------- Add ---------------------------*/
    @Test
    public void save_oneStationToAdd_theStationAddedIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.save(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.save(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }

    @Test
    public void save_oneStationToAddWithError_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.save(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.save(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest).isNull();


    }

    /*----------------------- update ---------------------------*/
    @Test
    public void updateByStation_aStationExist_firestationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByStation(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.updateByStation(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void updateByStation_aStationNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByStation(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.updateByStation(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(fireststationTest).isNull();

    }


    @Test
    public void updateByAddress_anAddressExist_firestationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByAddress(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.updateByAddress(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void updateByAddress_anAddressNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByAddress(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.updateByAddress(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(fireststationTest).isNull();

    }

    /*----------------------- Delete ---------------------------*/
    @Test
    public void delete_anAddressExist_firestationIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        List<Firestation>  firestationsResult = new ArrayList<>();
        firestations.add(new Firestation(correctAddress, correctStation));


        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(firestations);
        //WHEN
        firestationsResult = firestationService.delete(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(firestationsResult.size()).isEqualTo(1);
        //TODO ajouter des conditions

    }
    @Test
    public void delete_anAddressNotExist_nullIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(null);
        //WHEN
        firestations = firestationService.delete(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(firestations).isNull();

    }
    @Test
    public void delete_addressNotExist_nullIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(null);
        //WHEN
        firestations = firestationService.delete(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(firestations).isNull();

    }
}
