package com.safetynet.safetynetalerts.ut.alert;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.alert.AlertService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
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

    /*----------------------- get ---------------------------*/

    @Test
    public void getChildByAddress_anExistingAdressIsGiven_listOfchildAndHTTPCodeAreReturn() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(person);
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(personList);
 //       try {
            Mockito.when(medicalRecordDao.getChildByPersonList(any(List.class))).thenReturn(medicalRecordList);
  /*      } catch (ParseException e)
        {
            //TODO
        }
 */
        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(personList);
        //WHEN
        List<Person> childList = alertService.getChildByAddress(correctAddress);
        //THEN
        assertThat(childList.size()).isEqualTo(1);
        Person child = childList.get(0);
        assertThat(child.getFirstName()).isEqualTo(firstNameConst);
        assertThat(child.getLastName()).isEqualTo(lastNameConst);
    }

    @Test
    public void getChildByAddress_anInexistingAdressIsGiven_HTTPErrorCodeIsReturn() {
        //GIVEN
/*        List<Person> personList = new ArrayList<>();
        personList.add(person);
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);*/
        Mockito.when(personDao.getPersonByAdress(any(List.class))).thenReturn(null);
 //       try {
            Mockito.when(medicalRecordDao.getChildByPersonList(any(List.class))).thenReturn(null);
 /*       } catch (ParseException e)
        {
            //TODO
        }*/
        Mockito.when(personDao.getPersonByName(any(List.class))).thenReturn(null);
        //WHEN
        List<Person> childList = alertService.getChildByAddress(correctAddress);
        //THEN
        assertThat(childList.size()).isEqualTo(0);
        assertThat(childList).isEmpty();

    }


}
