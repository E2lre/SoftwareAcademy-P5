package com.safetynet.safetynetalerts.ut.person;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.person.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;
    @MockBean
    private Person personMock;
    @MockBean
    private InputDataReader inputDataReaderMock;

    //constantes de test
    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String addressConst = "Somewhere in US";
    String cityConst = "a US City";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "bill.gates@microsoft.com";

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
    //TODO Get personCOntroller TEST
    /**
     * Test : Controler Person
     * Get : get an existing person
     * @throws Exception
     */
    @Test
    public void PersonController_getAnExistingPerson_thePersonAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a person to get


        Mockito.when(personService.get(any(Person.class))).thenReturn(personMock);
        //WHEN //THEN return the person added
        mockMvc.perform(get("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bill"))
                .andExpect(jsonPath("$.lastName").value("Gates"));

    }

    /**
     * Test : Controler Person
     * Get : get an Inexisting person
     * @throws Exception
     */
    @Test
    public void PersonController_getAnInexistingPerson_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a person to add

        Mockito.when(personService.get(any(Person.class))).thenReturn(null);
        //WHEN //THEN return the person added
        mockMvc.perform(get("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /*------------------------ Post ---------------------------------*/
    /**
     * Test : Controler Person
     * POST : add an new person
     * @throws Exception
     */
    @Test
    public void PersonController_addANewPerson_theNewPersonAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a person to add


        Mockito.when(personService.add(any(Person.class))).thenReturn(personMock);
        //WHEN //THEN return the person added
        mockMvc.perform(post("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Bill"))
                .andExpect(jsonPath("$.lastName").value("Gates"));

    }

    /**
     * Test : Controler Person
     * POST : add an existing person
     * @throws Exception
     */
    @Test
    public void PersonController_addAnExistingPerson_thePersonIsNotCreatedAndHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a person to add

        Mockito.when(personService.add(any(Person.class))).thenReturn(null);
        //WHEN //THEN return the person added
        mockMvc.perform(post("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());
    }
    /*------------------------ Put ---------------------------------*/
    /**
     * Test : Controler Person
     * PUT : modify an exiting person
     * @throws Exception
     */
    @Test
    public void PersonController_updateAExistingPerson_personUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN Update an existing person
        Mockito.when(personService.update(any(Person.class))).thenReturn(personMock);

        // WHEN
        //THEN
        mockMvc.perform(put("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
               .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value("Bill"))
                .andExpect(jsonPath("$.lastName").value("Gates"));

    }

    /**
     * Test : Controler Person
     * PUT : modify an INexiting person
     * @throws Exception
     */
    @Test
    public void PersonController_updateAnInexistingPerson_personNotUpdatedAndHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN Update an Inexisting person
        Mockito.when(personService.update(any(Person.class))).thenReturn(null);

        // WHEN
        //THEN
        mockMvc.perform(put("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
               .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotModified());
    }
    /*------------------------ Delete ---------------------------------*/
    /**
     * Test : Controler Person
     * Delete : delete an exiting person
     * @throws Exception
     */
    @Test
    public void PersonController_deleteAExistingPerson_personDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN Delete an existing person
        List<Person> personList = new ArrayList<>();
        personList.add(personMock);

        Mockito.when(personService.delete(any(Person.class))).thenReturn(personList);

        // WHEN
        //THEN
        mockMvc.perform(delete("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
               .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        //TODO completer la liste et ajouter la suppression de plusieurs personnes à controler / demande???
    }

    /**
     * Test : Controler Person
     * Delete : delete an INexiting person
     * @throws Exception
     */
    @Test
    public void PersonController_deleteAnInexistingPerson_personNotDeletedAndHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN Delete an Inexisting person
        Mockito.when(personService.delete(any(Person.class))).thenReturn(null);

        // WHEN
        //THEN
        mockMvc.perform(delete("/person")
                .content(asJsonString(new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
               .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
