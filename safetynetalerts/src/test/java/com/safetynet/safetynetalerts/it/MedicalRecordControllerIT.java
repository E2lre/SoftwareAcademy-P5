package com.safetynet.safetynetalerts.it;

import com.safetynet.safetynetalerts.model.MedicalRecord;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerIT {

    @Autowired
    private MockMvc mockMvc;

    //constantes de test
    String firstnameConst = "John";
    String lastnameConst = "Boyd";
    String birthdateConst = "03/06/1984";
    List<String> medicationsListConst;
    List<String> allergiesListConst;

    String newFirstnameConst = "Bill";
    String newLastnameConst = "Gates";
    String newBirthdateConst = "28/10/1955";

    @BeforeEach
    public void setUpEach() {

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));

    }

    /*---------------------------------------- GET -------------------------------*/
    /**
     * Controler : medical record
     * Test : Get : get an existing medical record
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_anExistingMedicalRecordIsAsk_theMedicalRecordAskIsSended() throws Exception {

        //GIVEN : Give an exiting MedicalRecord

        //WHEN //THEN return the MedicalRecord
        mockMvc.perform(get("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstnameConst,lastnameConst,birthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstnameConst))
                .andExpect(jsonPath("$.lastName").value(lastnameConst))
                .andExpect(jsonPath("$.birthdate").value(birthdateConst));
//TODO lire la liste

    }

    /**
     * Controler : medical record
     * Test : Get : try to get an inexisting medical record
     * @throws Exception
     */
   @Test
    public void MedicalRecordController_anInexistingMedicalRecordIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexisting MedicalRecord
       String inexistingFirstnameConst ="John";
       String inexistingLastnameConst = "Doe";
        //WHEN //THEN return notfound

       mockMvc.perform(get("/medicalReport")
               .content(asJsonString(new MedicalRecord(inexistingFirstnameConst,inexistingLastnameConst,birthdateConst,medicationsListConst,allergiesListConst)))
               .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /*---------------------------------------- POST -------------------------------*/
    /**
     * Controler : medical record
     * Test : Add an new medical record
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_addANewMedicalRecord_theNewMedicalRecordAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an new MedicalRecord

        //WHEN : the MedicalRecord is add

        //THEN : MedicalRecord added
        mockMvc.perform(post("/medicalReport")
                .content(asJsonString(new MedicalRecord(newFirstnameConst,newLastnameConst,newBirthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(newFirstnameConst))
                .andExpect(jsonPath("$.lastName").value(newLastnameConst))
                .andExpect(jsonPath("$.birthdate").value(newBirthdateConst));
//TODO lire la liste

    }
    /**
     * Controler : MedicalRecord
     * Test : Add an new MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_addAnExistingMedicalRecord_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Existing MedicalRecord

        //WHEN : the MedicalRecord is not existing

        //THEN : error
        mockMvc.perform(post("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstnameConst,lastnameConst,birthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }
    /*---------------------------------------- PUT -------------------------------*/
    /**
     * Controler : MedicalRecord
     * Test : update an existing MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_updateAnExistingMedicalRecord_theUpdatedMedicalRecordAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an MedicalRecord to update

        //WHEN : the MedicalRecord is updated

        //THEN : MedicalRecord is updated
        mockMvc.perform(put("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstnameConst,lastnameConst,newBirthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value(firstnameConst))
                .andExpect(jsonPath("$.lastName").value(lastnameConst))
                .andExpect(jsonPath("$.birthdate").value(newBirthdateConst));

    }
    /**
     * Controler : MedicalRecord
     * Test : update an inexisting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_updateAnInexistingMedicalRecord_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Inexisting MedicalRecord
        String inexFirstnameConst = "Peter";
        String inexLastnameConst = "Xmas";
        //WHEN : the MedicalRecord is updated

        //THEN : error
        mockMvc.perform(put("/medicalReport")
                .content(asJsonString(new MedicalRecord(inexFirstnameConst,inexLastnameConst,newBirthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }

    /*---------------------------------------- DELETE -------------------------------*/
    /**
     * Controler : MedicalRecord
     * Test : Delete an existing MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_deleteAnExistingMedicalRecord_theDeletedMedicalRecordAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an person to delete
        String existingFirstnameConst ="Jacob";
        String existingLastnameConst = "Boyd";


        //WHEN : the person is delete

        //THEN : person is deleted
        mockMvc.perform(delete("/medicalReport")
                .content(asJsonString(new MedicalRecord(existingFirstnameConst,existingLastnameConst,newBirthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


    }
    /**
     * Controler : MedicalRecord
     * Test : delete an inexisting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_deleteAnInexistingMedicalRecord_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give an Inexisting MedicalRecord
        String inexFirstnameConst = "John";
        String inexLastnameConst = "doe";
        //WHEN : the MedicalRecord is delete

        //THEN : error
        mockMvc.perform(delete("/medicalReport")
                .content(asJsonString(new MedicalRecord(inexFirstnameConst,inexLastnameConst,newBirthdateConst,medicationsListConst,allergiesListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }
}
