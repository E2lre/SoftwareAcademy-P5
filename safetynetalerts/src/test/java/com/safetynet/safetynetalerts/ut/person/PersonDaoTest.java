package com.safetynet.safetynetalerts.ut.person;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PersonDaoTest {
    @Autowired
    private PersonDao personDao;
    //@MockBean
    private Person personMock;
    @MockBean
    private Person resultPerson;
/*
    @MockBean
    private InputDataReader inputDataReader;
*/

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

        Mockito.when(resultPerson.getFirstName()).thenReturn(firstNameConst);
        Mockito.when(resultPerson.getLastName()).thenReturn(lastNameConst);
        Mockito.when(resultPerson.getAddress()).thenReturn(addressConst);
        Mockito.when(resultPerson.getCity()).thenReturn(cityConst);
        Mockito.when(resultPerson.getZip()).thenReturn(zipConst);
        Mockito.when(resultPerson.getPhone()).thenReturn(phoneConst);
        Mockito.when(resultPerson.getEmail()).thenReturn(emailConst);
        Mockito.when(resultPerson.toString()).thenReturn("Person [firstName=" + firstNameConst + ", lastName=" + lastNameConst + ", address=" + addressConst + ", city=" + cityConst + ", zip=" + zipConst + ", phone=" + phoneConst + ", email=" + emailConst + "]");


    }
    /*------------------------ Get ---------------------------------*/

    /**
     * PersonDAO
     * Test : Get en existing person
     */
    @Test
    public void get_existingFirstnameLastnameGiven_thePersonIsReturn() {
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
    public void get_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        personMock.setLastName(lastNameInexistingConst);
        //WHEN
        Person personDaoTest = personDao.get(personMock);
        //THEN
        assertThat(personDaoTest).isNull();
    }
    /*------------------------ Add ---------------------------------*/

    /**
     * PersonDAO
     * Test : add en INexisting person
     */
    @Test
    public void add_inexistingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        personMock.setLastName(lastNameInexistingConst);
        //WHEN
        Person personDaoTest = personDao.add(personMock);
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
    public void add_existingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        personMock.setLastName(lastNameConst);
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
    public void update_existingFirstnameLastnameGiven_thePersonIsReturn() {
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
    public void update_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        personMock.setLastName(lastNameInexistingConst);
        //WHEN
        Person personDaoTest = personDao.update(personMock);
        //THEN
        assertThat(personDaoTest).isNull();
    }
    /*------------------------ Delete ---------------------------------*/

    /**
     * PersonDAO
     * Test : delete en existing person
     */
    @Test
    public void delete_existingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        //WHEN
        List<Person> personDaoTest = personDao.delete(personMock);
        //THEN
        assertThat(personDaoTest.size()).isEqualTo(1);

    }

    /**
     * PersonDAO
     * Test : delete an Inexisting person
     */
    @Test
    public void delete_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        personMock.setLastName(lastNameInexistingConst);
        //WHEN
        List<Person> personDaoTest = personDao.delete(personMock);
        //THEN
        assertThat(personDaoTest).isNull();
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