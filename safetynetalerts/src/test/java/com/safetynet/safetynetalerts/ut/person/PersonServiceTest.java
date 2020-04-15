package com.safetynet.safetynetalerts.ut.person;

import com.safetynet.safetynetalerts.dao.PersonDao;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.person.PersonService;
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

@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService personService;

    @MockBean
    private PersonDao personDao;

    @MockBean
    private InputDataReader inputDataReader;

    private Person personMock;

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

    }

    /*------------------------ Get ---------------------------------*/
    /**
     * PersonService
     * Test : Get en existing person
     */
    @Test
    public void get_existingFirstnameLastnameGiven_thePersonIsReturn(){
        //GIVEN
        Mockito.when(personDao.get(any(Person.class))).thenReturn(personMock);
        //WHEN
        Person personServiceTest = personService.get(personMock);
        //THEN
        assertThat(personServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personServiceTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(personServiceTest.getAddress()).isEqualTo(addressConst);
        assertThat(personServiceTest.getCity()).isEqualTo(cityConst);
        assertThat(personServiceTest.getZip()).isEqualTo(zipConst);
        assertThat(personServiceTest.getPhone()).isEqualTo(phoneConst);
        assertThat(personServiceTest.getEmail()).isEqualTo(emailConst);


    }
    /**
     * PersonService
     * Test : Get en INexisting person
     */
    @Test
    public void get_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(personDao.get(any(Person.class))).thenReturn(null);
        //WHEN
        Person personServiceTest = personService.get(personMock);
        //THEN
        assertThat(personServiceTest).isNull();
    }

    /*------------------------ Add ---------------------------------*/
    /**
     * PersonService
     * Test : add en INexisting person
     */
    @Test
    public void add_inexistingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        Mockito.when(personDao.add(any(Person.class))).thenReturn(personMock);
        //WHEN
        Person personServiceTest = personService.add(personMock);
        //THEN
        assertThat(personServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personServiceTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(personServiceTest.getAddress()).isEqualTo(addressConst);
        assertThat(personServiceTest.getCity()).isEqualTo(cityConst);
        assertThat(personServiceTest.getZip()).isEqualTo(zipConst);
        assertThat(personServiceTest.getPhone()).isEqualTo(phoneConst);
        assertThat(personServiceTest.getEmail()).isEqualTo(emailConst);
    }

    /**
     * PersonService
     * Test : add en existing person
     */
    @Test
    public void add_existingFirstnameLastnameGiven_tnullIsReturn() {
        //GIVEN
        Mockito.when(personDao.add(any(Person.class))).thenReturn(null);
        //WHEN
        Person personServiceTest = personService.add(personMock);
        //THEN
        assertThat(personServiceTest).isNull();
    }
    /*------------------------ Update ---------------------------------*/
    /**
     * PersonService
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
        Mockito.when(personDao.update(any(Person.class))).thenReturn(personMock);

        //WHEN
        Person personServiceTest = personService.update(personMock);
        //THEN
        assertThat(personServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(personServiceTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(personServiceTest.getAddress()).isEqualTo(newAddressConst);
        assertThat(personServiceTest.getCity()).isEqualTo(newCityConst);
        assertThat(personServiceTest.getZip()).isEqualTo(newZipConst);
        assertThat(personServiceTest.getPhone()).isEqualTo(newPhoneConst);
        assertThat(personServiceTest.getEmail()).isEqualTo(newEmailConst);
    }

    /**
     * PersonService
     * Test : update an Inexisting person
     */
    @Test
    public void update_inexistingFirstnameLastnameGiven_tnullIsReturn() {
        //GIVEN
        Mockito.when(personDao.update(any(Person.class))).thenReturn(null);
        //WHEN
        Person personServiceTest = personService.update(personMock);
        //THEN
        assertThat(personServiceTest).isNull();
    }

    /*------------------------ Delete ---------------------------------*/
    /**
     * PersonService
     * Test : delete en existing person
     */
    @Test
    public void delete_existingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        List<Person> personList = new ArrayList<>();
        personList.add(personMock);
        Mockito.when(personDao.delete(any(Person.class))).thenReturn(personList);
        //WHEN
        List<Person> personServiceTest = personService.delete(personMock);
        //THEN
        assertThat(personServiceTest.size()).isEqualTo(1);

    }
    /**
     * PersonService
     * Test : delete an Inexisting person
     */
    @Test
    public void delete_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(personDao.delete(any(Person.class))).thenReturn(null);
        //WHEN
        List<Person> personServiceTest = personService.delete(personMock);
        //THEN
        assertThat(personServiceTest).isNull();
    }
}
