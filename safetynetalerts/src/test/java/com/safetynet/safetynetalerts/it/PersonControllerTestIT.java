package com.safetynet.safetynetalerts.it;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    //Test constantes
    String firstnameConst = "John";
    String lastnameConst = "Boyd";
    String addressConst = "1509 Culver St";
    String cityConst = "Culver";
    String zipConst = "97451";
    String phoneConst = "841-874-6512";
    String emailConst = "jaboyd@email.com";
    String newFirstnameConst = "Bill";
    String newLastnameConst = "Gates";
    String newAddressConst = "Building 92";
    String newCityConst = "Redmond";
    String newZipConst = "99999";
    String newPhoneConst = "0123456789";
    String newEmailConst = "bill.gates@microsoft.com";

    @BeforeEach
    public void setUpEach() {

    }
    /*---------------------------------------- GET -------------------------------*/
    @Test
    public void PersonController_getExistingPerson_thePersonAskIsSended() throws Exception {

        //GIVEN : Give an exiting Person

        //WHEN //THEN return the station
        mockMvc.perform(get("/person")
                .content(asJsonString(new Person(firstnameConst,lastnameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstnameConst))
                .andExpect(jsonPath("$.lastName").value(lastnameConst))
                .andExpect(jsonPath("$.address").value(addressConst))
                .andExpect(jsonPath("$.city").value(cityConst))
                .andExpect(jsonPath("$.zip").value(zipConst))
                .andExpect(jsonPath("$.phone").value(phoneConst))
                .andExpect(jsonPath("$.email").value(emailConst));
    }

    /**
     * Controler : person
     * Test : Get : try to get an inexisting address
     * @throws Exception
     */
  @Test
    public void PersonController_getInexistingPerson_errorIsSended() throws Exception {

        //GIVEN : Give an inexisting person
        String inexistingFirstnameConst = "Peter";
        String inexistingastnameConst = "Xmas";

        //WHEN //THEN return notfound
        mockMvc.perform(get("/person")
                .content(asJsonString(new Person(inexistingFirstnameConst,inexistingastnameConst,newAddressConst,newCityConst,newZipConst,newPhoneConst,newEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
;
    }

    /*---------------------------------------- POST -------------------------------*/
    /**
     * Controler : Person
     * Test : Add an new address
     * @throws Exception
     */
    @Test
    public void PersonController_addANewPerson_theNewPersonAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an new person

        //WHEN : the person is created

        //THEN : person created
        mockMvc.perform(post("/person")
                .content(asJsonString(new Person(newFirstnameConst,newLastnameConst,newAddressConst,newCityConst,newZipConst,newPhoneConst,newEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(newFirstnameConst))
                .andExpect(jsonPath("$.lastName").value(newLastnameConst))
                .andExpect(jsonPath("$.address").value(newAddressConst))
                .andExpect(jsonPath("$.city").value(newCityConst))
                .andExpect(jsonPath("$.zip").value(newZipConst))
                .andExpect(jsonPath("$.phone").value(newPhoneConst))
                .andExpect(jsonPath("$.email").value(newEmailConst));

    }

    /**
     * Controler : Person
     * Test : Add an new address
     * @throws Exception
     */
    @Test
    public void PersonController_addAnExistingPerson_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Existing person

        //WHEN : the person is created

        //THEN : error
        mockMvc.perform(post("/person")
                .content(asJsonString(new Person(firstnameConst,lastnameConst,addressConst,cityConst,zipConst,phoneConst,emailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }

    /*---------------------------------------- PUT -------------------------------*/
    /**
     * Controler : Person
     * Test : update an existing person
     * @throws Exception
     */
    @Test
    public void PersonController_updateAnExistingPerson_theUpdatedPersonAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an person to update

        //WHEN : the person is updated

        //THEN : person is updated
        mockMvc.perform(put("/person")
                .content(asJsonString(new Person(firstnameConst,lastnameConst,newAddressConst,newCityConst,newZipConst,newPhoneConst,newEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value(firstnameConst))
                .andExpect(jsonPath("$.lastName").value(lastnameConst))
                .andExpect(jsonPath("$.address").value(newAddressConst))
                .andExpect(jsonPath("$.city").value(newCityConst))
                .andExpect(jsonPath("$.zip").value(newZipConst))
                .andExpect(jsonPath("$.phone").value(newPhoneConst))
                .andExpect(jsonPath("$.email").value(newEmailConst));

    }

    /**
     * Controler : Person
     * Test : update an inexisting person
     * @throws Exception
     */
    @Test
    public void PersonController_updateAnInexistingPerson_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Inexisting person
        String inexFirstnameConst = "Peter";
        String inexLastnameConst = "Xmas";
        //WHEN : the person is updated

        //THEN : error
        mockMvc.perform(put("/person")
                .content(asJsonString(new Person(inexFirstnameConst,inexLastnameConst,newAddressConst,newCityConst,newZipConst,newPhoneConst,newEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }

    /*---------------------------------------- DELETE -------------------------------*/
    /**
     * Controler : Person
     * Test : Delete an existing person
     * @throws Exception
     */
    @Test
    public void PersonController_deleteAnExistingPerson_theDeletedPersonAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an person to delete
        String existingFirstnameConst ="Jacob";
        String existingLastnameConst = "Boyd";
        String existingAddressConst = "1509 Culver St";
        String existingCityConst = "Culver";
        String existingZipConst = "97451";
        String existingPhoneConst = "841-874-6512";
        String existingEmailConst = "jaboyd@email.com";

        //WHEN : the person is delete

        //THEN : person is deleted
        mockMvc.perform(delete("/person")
                .content(asJsonString(new Person(existingFirstnameConst,existingLastnameConst,existingAddressConst,existingCityConst,existingZipConst,existingPhoneConst,existingEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
/*                .andExpect(jsonPath("$.firstName").value(existingFirstnameConst))
                .andExpect(jsonPath("$.lastName").value(existingLastnameConst))
                .andExpect(jsonPath("$.address").value(existingAddressConst))
                .andExpect(jsonPath("$.city").value(existingCityConst))
                .andExpect(jsonPath("$.zip").value(existingZipConst))
                .andExpect(jsonPath("$.phone").value(existingPhoneConst))
                .andExpect(jsonPath("$.email").value(existingEmailConst));*/

    }

    /**
     * Controler : Person
     * Test : delete an inexisting person
     * @throws Exception
     */
    @Test
    public void PersonController_deleteAnInexistingPerson_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Inexisting person
        String inexFirstnameConst = "John";
        String inexLastnameConst = "doe";
        //WHEN : the person is delete

        //THEN : error
        mockMvc.perform(delete("/person")
                .content(asJsonString(new Person(inexFirstnameConst,inexLastnameConst,newAddressConst,newCityConst,newZipConst,newPhoneConst,newEmailConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}
