package com.safetynet.safetynetalerts.ut.complement;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Email;
import com.safetynet.safetynetalerts.model.detail.Fire;
import com.safetynet.safetynetalerts.model.detail.Flood;
import com.safetynet.safetynetalerts.model.detail.PersonInfo;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.complement.ComplementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class ComplementServiceTest {
    @Autowired
    private ComplementService complementService;
   /* @MockBean
    private ComplementService complementServiceMock;*/
    @MockBean
    private MedicalRecordDao medicalRecordDao;
    @MockBean
    private PersonDao personDao;
    @MockBean
    private FirestationDao firestationDao;
    @MockBean
    private InputDataReader inputDataReader;

    private Firestation firestation;
    private Person person;
    private MedicalRecord medicalRecord;
    //constantes de test


    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String addressConst = "Somewhere in US";
    String cityConst = "a US City";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "bill.gates@microsoft.com";

    String existingAddressConst = addressConst;
    String inexistingAddressConst = "pouldlar land";

    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;

    @BeforeEach
    private void setUpPerTest() {

        firestation = new Firestation(addressConst,"2");

        person = new Person();
        person.setFirstName(firstNameConst);
        person.setLastName(lastNameConst);
        person.setAddress(addressConst);
        person.setCity(cityConst);
        person.setZip(zipConst);
        person.setPhone(phoneConst);
        person.setEmail(emailConst);

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));


        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameConst);
        medicalRecord.setLastName(lastNameConst);
        medicalRecord.setBirthdate(birthdateConst);
        medicalRecord.setAllergies(allergiesListConst);
        medicalRecord.setMedications(medicationsListConst);

    }
    /*----------------------- get Fire ---------------------------*/

    @Test
    public void getFireByAddress_anExistingAdressIsGiven_listOfFiredIsReturn() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(firestationDao.findByAddress(anyString())).thenReturn(firestation);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personList);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(medicalRecord);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);
        //WHEN
        List<Fire> fireList = complementService.getFireByAddress(existingAddressConst);
        //THEN
        assertThat(fireList.size()).isEqualTo(1);
        Fire fire = fireList.get(0);
        assertThat(fire.getAge()).isEqualTo("10");
        assertThat(fire.getStationNumber()).isEqualTo("2");

    }
    @Test
    public void getFireByAddress_anInexistingAdressIsGiven_emptyListsReturn() {
        //GIVEN

        Mockito.when(firestationDao.findByAddress(anyString())).thenReturn(null);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);
        //WHEN
        List<Fire> fireList = complementService.getFireByAddress(inexistingAddressConst);
        //THEN
        assertThat(fireList.size()).isEqualTo(0);


    }

    /*----------------------- get Flood ---------------------------*/

    @Test
    public void getFloodByStationList_anExistingStationListIsGiven_listOfFloodIsReturn() {
        //GIVEN
        List<String> stationList = new ArrayList<>();
        stationList.add("1");
        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(firestation);

        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(firestationList);
        List<Fire> fireList = new ArrayList<>();
        fireList.add(new Fire("1",firstNameConst,lastNameConst,phoneConst,"25",medicationsListConst,allergiesListConst));
        //Mockito.when(complementServiceMock.getFireByAddress(existingAddressConst)).thenReturn(fireList);

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(firestationDao.findByAddress(anyString())).thenReturn(firestation);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personList);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(medicalRecord);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);

        //WHEN
        List<Flood> floodList = complementService.getFloodByStationList(stationList);
        //THEN
        assertThat(floodList.size()).isEqualTo(1);
        Flood flood = floodList.get(0);
        assertThat(flood.getAddress()).isEqualTo(addressConst);
        assertThat(flood.getFire().size()).isEqualTo(1);

    }
    @Test
    public void getFloodByStationList_anInexistingStationIsGiven_emptyListIsReturn() {
        //GIVEN
        List<String> stationList = new ArrayList<>();
        stationList.add("1");
        List<Firestation> firestationList = new ArrayList<>();
        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(firestationList);

        Mockito.when(firestationDao.findByAddress(anyString())).thenReturn(null);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);
        //WHEN
        List<Flood> floodList = complementService.getFloodByStationList(stationList);
        //THEN
        assertThat(floodList.size()).isEqualTo(0);


    }

    /*----------------------- get PersonInfo ---------------------------*/

    @Test
    public void getPersonInfoByFisrtNameLastName_anExistingFirstNameLastName_listOfPersonInfoIsReturn() {
        //GIVEN

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(personList);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(medicalRecord);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(25);

        //WHEN
        List<PersonInfo> personInfoList = complementService.getPersonInfoByFisrtNameLastName(firstNameConst,lastNameConst);
        //THEN
        assertThat(personInfoList.size()).isEqualTo(1);


    }
    @Test
    public void getPersonInfoByFisrtNameLastName_anInexistingSFirstNameLastName_emptyListIsReturn() {
        //GIVEN

        List<Person> personList = new ArrayList<>();
        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(personList);
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(25);

        //WHEN
        List<PersonInfo> personInfoList = complementService.getPersonInfoByFisrtNameLastName(firstNameConst,lastNameConst);
        //THEN
        assertThat(personInfoList.size()).isEqualTo(0);

    }
    /*----------------------- get Email by City ---------------------------*/

    @Test
    public void getEmailByCity_anExistingCity_listOfEmailIsReturn() {
        //GIVEN

        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(personDao.getPersonByCity(anyString())).thenReturn(personList);


        //WHEN
        List<Email> emailList = complementService.getEmailByCity(cityConst);
        //THEN
        assertThat(emailList.size()).isEqualTo(1);


    }
    @Test
    public void getEmailByCity_anInexistingCity_emptyListIsReturn() {
        //GIVEN

        List<Person> personList = new ArrayList<>();
        Mockito.when(personDao.getPersonByCity(anyString())).thenReturn(personList);


        //WHEN
        List<Email> emailList = complementService.getEmailByCity(cityConst);
        //THEN
        assertThat(emailList.size()).isEqualTo(0);



    }
}
