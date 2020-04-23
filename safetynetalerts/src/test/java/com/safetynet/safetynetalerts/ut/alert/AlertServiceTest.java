package com.safetynet.safetynetalerts.ut.alert;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Child;
import com.safetynet.safetynetalerts.model.detail.Childs;
import com.safetynet.safetynetalerts.model.detail.Phone;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.alert.AlertService;
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
public class AlertServiceTest {

    @Autowired
    private AlertService alertService;
    @MockBean
    private MedicalRecordDao medicalRecordDao;
    @MockBean
    private PersonDao personDao;
    @MockBean
    private FirestationDao firestationDao;
    @MockBean
    private InputDataReader inputDataReader;

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
    String correctAddress = "Correct Address";
    String correctStation ="1";
    String incorrectAddress = "Incorrect Address";
    String incorrectStation ="99";

    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;


    @BeforeEach
    private void setUpPerTest() {
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

    /*----------------------- get Child ---------------------------*/

    @Test
    public void getChildByAddress_anExistingAdressIsGiven_listOfChildIsReturn() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personList);

        Mockito.when(medicalRecordDao.getChildByPersonList(any(List.class))).thenReturn(medicalRecordList);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);
        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(personList);
        //WHEN
        Childs childList = alertService.getChildByAddress(correctAddress);
        //THEN
        assertThat(childList.getChilds().size()).isEqualTo(1);
        Child child = childList.getChilds().get(0);
        assertThat(child.getFirstName()).isEqualTo(firstNameConst);
        assertThat(child.getLastName()).isEqualTo(lastNameConst);
    }

    @Test
    public void getChildByAddress_anInexistingAdressIsGiven_nullIsReturn() {
        //GIVEN

        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(null);

        Mockito.when(medicalRecordDao.getChildByPersonList(any(List.class))).thenReturn(null);

        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(null);
        Mockito.when(medicalRecordDao.getAgeByPerson(any(Person.class))).thenReturn(10);
        //WHEN
        Childs childList = alertService.getChildByAddress(correctAddress);

        //THEN
        assertThat(childList.getChilds()).isNull();
        assertThat(childList.getPersons()).isNull();

    }

    /*----------------------- get Phone ---------------------------*/

    @Test
    public void getPhoneByStation_anExistingAdressIsGiven_listOfPhoneIsReturn() {
        //GIVEN
        List<Firestation> firestationList = new ArrayList<>();
        firestationList.add(new Firestation(addressConst,"2"));
        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(firestationList);
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personList);

        //WHEN
        List<Phone> phoneList = alertService.getPhoneByStation("2");

        //THEN
        assertThat(phoneList.size()).isEqualTo(1);
        Phone phone = phoneList.get(0);
        assertThat(phone.getFirstName()).isEqualTo(firstNameConst);
        assertThat(phone.getLastName()).isEqualTo(lastNameConst);
    }

    @Test
    public void getPhoneByStation_anInexistingAdressIsGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(firestationDao.getFirestationListByStation(anyString())).thenReturn(null);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(null);

        //WHEN
        List<Phone> phoneList = alertService.getPhoneByStation("2");

        //THEN
        assertThat(phoneList.size()).isEqualTo(0);
        assertThat(phoneList).isEmpty();

    }
}
