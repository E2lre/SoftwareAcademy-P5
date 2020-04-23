package com.safetynet.safetynetalerts.ut.person;


import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.dao.PersonDaoImpl;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonDaoTest {

    @TestConfiguration
    static class PersonDaoImplTestContextConfiguration {
        @Bean
        public PersonDao personDao () {
            return new PersonDaoImpl();
        }
    }
    @Autowired
    private PersonDao personDao;
    @MockBean
    private Person person;

    private Person personMock;
    private Person existingPerson;
    private Person inexistingPerson;
    @MockBean
    private InputDataReader inputDataReader;

    //constantes de test
    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String addressConst = "Somewhere in US";
    String cityConst = "a US City";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "bill.gates@microsoft.com";

    String lastNameInexistingConst = "Invisible Man";
    String newAddressConst = "Somewhere in the world";
    String newCityConst = "a world City";
    String newZipConst = "www";
    String newPhoneConst = "9999999999";
    String newEmailConst = "world@world.com";

    @BeforeEach
    public void setUpEach() {

        personMock = new Person();
        personMock.setFirstName(firstNameConst);
        personMock.setLastName(lastNameConst);
        personMock.setAddress(addressConst);
        personMock.setCity(cityConst);
        personMock.setZip(zipConst);
        personMock.setPhone(phoneConst);
        personMock.setEmail(emailConst);

        existingPerson = new Person();
        existingPerson.setFirstName(firstNameConst);
        existingPerson.setLastName(lastNameConst);
        existingPerson.setAddress(addressConst);
        existingPerson.setCity(cityConst);
        existingPerson.setZip(zipConst);
        existingPerson.setPhone(phoneConst);
        existingPerson.setEmail(emailConst);

        inexistingPerson = new Person();
        inexistingPerson.setFirstName(firstNameConst);
        inexistingPerson.setLastName(lastNameInexistingConst);
        inexistingPerson.setAddress(addressConst);
        inexistingPerson.setCity(cityConst);
        inexistingPerson.setZip(zipConst);
        inexistingPerson.setPhone(phoneConst);
        inexistingPerson.setEmail(emailConst);


        List<Person> existingPersonList = new ArrayList<>();
        existingPersonList.add(existingPerson);
        boolean personLoadResult = personDao.load(existingPersonList);

        Mockito.when(person.getFirstName()).thenReturn(firstNameConst);
        Mockito.when(person.getLastName()).thenReturn(lastNameConst);
        Mockito.when(person.getAddress()).thenReturn(addressConst);
        Mockito.when(person.getCity()).thenReturn(cityConst);
        Mockito.when(person.getZip()).thenReturn(zipConst);
        Mockito.when(person.getPhone()).thenReturn(phoneConst);
        Mockito.when(person.getEmail()).thenReturn(emailConst);
        Mockito.when(person.toString()).thenReturn("Person [firstName=" + firstNameConst + ", lastName=" + lastNameConst + ", address=" + addressConst + ", city=" + cityConst + ", zip=" + zipConst + ", phone=" + phoneConst + ", email=" + emailConst + "]");

    }
    @AfterEach
    private void cleanUpEach() {

        personDao.clear();
    }
    /*------------------------ Get ---------------------------------*/

    /**
     * PersonDAO
     * Test : Get en existing person
     */
    @Test
    public void get_existingFirstNameLastNameGiven_thePersonIsReturn() {
        //GIVEN

        //WHEN
        Person personDaoTest = personDao.get(personMock);
        //THEN
        assertThat(personDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personDaoTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(personDaoTest.getAddress()).isEqualTo(addressConst);
        assertThat(personDaoTest.getCity()).isEqualTo(cityConst);
        assertThat(personDaoTest.getZip()).isEqualTo(zipConst);
        assertThat(personDaoTest.getPhone()).isEqualTo(phoneConst);
        assertThat(personDaoTest.getEmail()).isEqualTo(emailConst);


    }

    /**
     * PersonDAO
     * Test : Get en INexisting person
     */
    @Test
    public void get_inexistingFirstNameLastNameGiven_nullIsReturn() {
        //GIVEN


        //WHEN
        Person personDaoTest = personDao.get(inexistingPerson);
        //THEN
        assertThat(personDaoTest).isNull();
    }

    /**
     * PersonDAO
     * Test : getPersonByAdress with a existing adress
     */
    @Test
    public void getPersonByAdress_existingAdressGiven_personListReturn(){
        //GIVEN
        Person personDaoTest = personDao.add(existingPerson);
        personDaoTest = personDao.add(inexistingPerson);
        List<String> addressList = new ArrayList<>();
        addressList.add(existingPerson.getAddress()); //same address for existingPerson & inexistingPerson

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByAdress(addressList);
        //THEN
        assertThat(personDaoListTest.size()).isEqualTo(2);

    }

    /**
     * PersonDAO
     * Test : getPersonByAdress with a inexisting adress
     */
    @Test
    public void getPersonByAdress_inexistingAdressGiven_emptyReturn(){
        //GIVEN
        Person personDaoTest = personDao.add(existingPerson);
        personDaoTest = personDao.add(inexistingPerson);
        List<String> addressList = new ArrayList<>();
        addressList.add("0"); //same address for existingPerson & inexistingPerson

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByAdress(addressList);
        //THEN
        assertThat(personDaoListTest).isEmpty();

    }


    /**
     * PersonDAO
     * Test : getPersonByName with a existing Name
     */
    @Test
    public void getPersonByName_existingNameGiven_personListReturn(){
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(existingPerson);

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByName(personList);
        //THEN
        assertThat(personDaoListTest.size()).isEqualTo(1);

    }

    /**
     * PersonDAO
     * Test : getPersonByName with a inexisting Name
     */
    @Test
    public void getPersonByName_inexistingNameGiven_emptyReturn(){
        List<Person> personList = new ArrayList<>();
        personList.add(inexistingPerson);

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByName(personList);
        //THEN
        assertThat(personDaoListTest).isEmpty();

    }

    /**
     * PersonDAO
     * Test : getPersonByCity with a existing City
     */
    @Test
    public void getPersonByCity_existingCityGiven_personListReturn(){
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(existingPerson);

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByCity(cityConst);
        //THEN
        assertThat(personDaoListTest.size()).isEqualTo(1);

    }

    /**
     * PersonDAO
     * Test : getPersonByCity with a inexisting City
     */
    @Test
    public void getPersonByCity_inexistingCityGiven_emptyReturn(){
        List<Person> personList = new ArrayList<>();
        personList.add(inexistingPerson);

        //WHEN
        List<Person> personDaoListTest = personDao.getPersonByCity(newCityConst);
        //THEN
        assertThat(personDaoListTest).isEmpty();

    }
    /*------------------------ Add ---------------------------------*/

    /**
     * PersonDAO
     * Test : add en INexisting person
     */
    @Test
    public void add_inexistingFirstNameLastNameGiven_thePersonIsReturn() {
        //GIVEN

        //WHEN
        Person personDaoTest = personDao.add(inexistingPerson);
        //THEN
        assertThat(personDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personDaoTest.getLastName()).isEqualTo(lastNameInexistingConst);
        assertThat(personDaoTest.getAddress()).isEqualTo(addressConst);
        assertThat(personDaoTest.getCity()).isEqualTo(cityConst);
        assertThat(personDaoTest.getZip()).isEqualTo(zipConst);
        assertThat(personDaoTest.getPhone()).isEqualTo(phoneConst);
        assertThat(personDaoTest.getEmail()).isEqualTo(emailConst);
    }

    /**
     * PersonDAO
     * Test : add en existing person
     */
    @Test
    public void add_existingFirstNameLastNameGiven_nullIsReturn() {
        //GIVEN

        //WHEN
        Person personDaoTest = personDao.add(personMock);
        //THEN
        assertThat(personDaoTest).isNull();
    }

    /*------------------------ Update ---------------------------------*/

    /**
     * PersonDAO
     * Test : update en existing person
     */
    @Test
    public void update_existingFirstNameLastNameGiven_thePersonIsReturn() {
        //GIVEN
        personMock.setAddress(newAddressConst);
        personMock.setCity(newCityConst);
        personMock.setZip(newZipConst);
        personMock.setPhone(newPhoneConst);
        personMock.setEmail(newEmailConst);

        //WHEN
        Person personDaoTest = personDao.update(personMock);
        //THEN
        assertThat(personDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personDaoTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(personDaoTest.getAddress()).isEqualTo(newAddressConst);
        assertThat(personDaoTest.getCity()).isEqualTo(newCityConst);
        assertThat(personDaoTest.getZip()).isEqualTo(newZipConst);
        assertThat(personDaoTest.getPhone()).isEqualTo(newPhoneConst);
        assertThat(personDaoTest.getEmail()).isEqualTo(newEmailConst);
    }

    /**
     * PersonDAO
     * Test : update an Inexisting person
     */
    @Test
    public void update_inexistingFirstNameLastNameGiven_nullIsReturn() {
        //GIVEN

        //WHEN
        Person personDaoTest = personDao.update(inexistingPerson);
        //THEN
        assertThat(personDaoTest).isNull();
    }
    /*------------------------ Delete ---------------------------------*/

    /**
     * PersonDAO
     * Test : delete en existing person
     */
    @Test
    public void delete_existingFirstNameLastNameGiven_thePersonIsReturn() {
        //GIVEN add a second person
        Person personDaoTest = personDao.add(inexistingPerson);
        //WHEN
        List<Person> personDaoTestList = personDao.delete(personMock);
        //THEN
        assertThat(personDaoTestList.size()).isEqualTo(1);

    }

    /**
     * PersonDAO
     * Test : delete an Inexisting person
     */
    @Test
    public void delete_inexistingFirstNameLastNameGiven_nullIsReturn() {
        //GIVEN

        //WHEN
        List<Person> personDaoTest = personDao.delete(inexistingPerson);
        //THEN
        assertThat(personDaoTest).isEmpty();
    }
    /*------------------------ Load ---------------------------------*/

    /**
     * PersonDAO
     * Test : load a personlist
     */
    @Test
    public void load_personListGiven_trueIsReturn() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(personMock);
        //WHEN
        boolean personLoadResult = personDao.load(personList);
        //THEN
        assertThat(personLoadResult).isTrue();


    }


}