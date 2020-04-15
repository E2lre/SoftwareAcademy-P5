package com.safetynet.safetynetalerts.ut.medicalrecord;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.medicalrecord.MedicalRecordService;
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
import java.util.Arrays;
import java.util.List;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;
    @MockBean
    private MedicalRecord medicalRecord;
    @MockBean
    private InputDataReader inputDataReaderMock;

    //constantes de test
    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;
    String lastNameInexistingConst = "Invisible Man";

    @BeforeEach
    public void setUpEach() {

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));


        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameConst);
        medicalRecord.setLastName(lastNameConst);
        medicalRecord.setBirthdate(birthdateConst);
        medicalRecord.setAllergies(allergiesListConst);
        medicalRecord.setMedications(medicationsListConst);
    }

    /*------------------------ Get ---------------------------------*/
    /**
     * Test : Controler MedicalRecord
     * GET : get a MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_getAnExistingwMedicalRecord_theMedicalRecordAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a medical record

        Mockito.when(medicalRecordService.get(any(MedicalRecord.class))).thenReturn(medicalRecord);
        //WHEN //THEN return the medical record ask
        mockMvc.perform(get("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst))
                .andExpect(jsonPath("$.birthdate").value(birthdateConst));

    }

    /**
     * Test : Controler MedicalRecord
     * GET : get an inexisting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_getAnInexistingMedicalRecord_anHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a medical record

        Mockito.when(medicalRecordService.add(any(MedicalRecord.class))).thenReturn(null);
        //WHEN //THEN return error
        mockMvc.perform(get("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /*------------------------ Post ---------------------------------*/
    /**
     * Test : Controler MedicalRecord
     * POST : add an new MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_addANewMedicalRecord_theNewMedicalRecordAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a medical record to add

        Mockito.when(medicalRecordService.add(any(MedicalRecord.class))).thenReturn(medicalRecord);
        //WHEN //THEN return the medical record
        mockMvc.perform(post("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst))
                .andExpect(jsonPath("$.birthdate").value(birthdateConst));

    }

    /**
     * Test : Controler MedicalRecord
     * POST : add an existing MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_addAnExistingMedicalRecord_theMedicalRecordIsNotCreatedAndHTTPErrorCodeAreReturn() throws Exception {
        //GIVEN : Give a medical record to add

        Mockito.when(medicalRecordService.add(any(MedicalRecord.class))).thenReturn(null);
        //WHEN //THEN return error
        mockMvc.perform(post("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());
    }

    /*------------------------ Put ---------------------------------*/
    /**
     * Test : Controler MedicalRecord
     * PUT : modify an exiting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_updateAExistingMedicalRecord_medicalRecordUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN Update an existing person
        medicalRecord.setBirthdate("01/01/1980");
        medicationsListConst.add("acide acétylsalicylique");
        medicalRecord.setMedications(medicationsListConst);
        Mockito.when(medicalRecordService.update(any(MedicalRecord.class))).thenReturn(medicalRecord);

        // WHEN
        //THEN
        mockMvc.perform(put("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstName").value(firstNameConst))
                .andExpect(jsonPath("$.lastName").value(lastNameConst))
                .andExpect(jsonPath("$.birthdate").value("01/01/1980"));

    }

    /**
     * Test : Controler MedicalRecord
     * PUT : modify an INexiting MedicalRecord
     * @throws Exception
     */
    @Test
    public void PersonController_updateAnInexistingMedicalRecord_medicalRecordNotUpdatedAndHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN Update an Inexisting person
        Mockito.when(medicalRecordService.update(any(MedicalRecord.class))).thenReturn(null);

        // WHEN
        //THEN
        mockMvc.perform(put("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotModified());
    }
    /*------------------------ Delete ---------------------------------*/
    /**
     * Test : Controler MedicalRecord
     * Delete : delete an exiting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_deleteAExistingMedicalRecord_medicalRecordDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN Delete an existing MedicalRecord
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);

        Mockito.when(medicalRecordService.delete(any(MedicalRecord.class))).thenReturn(medicalRecordList);

        // WHEN
        //THEN
        mockMvc.perform(delete("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //TODO completer la liste et ajouter la suppression de plusieurs personnes à controler / demande???
    }

    /**
     * Test : Controler MedicalRecord
     * Delete : delete an INexiting MedicalRecord
     * @throws Exception
     */
    @Test
    public void MedicalRecordController_deleteAnInexistingMedicalRecord_medicalRecordNotDeletedAndHTTPErrorCodeIsReturn() throws Exception {
        //GIVEN Delete an Inexisting MedicalRecord
        Mockito.when(medicalRecordService.delete(any(MedicalRecord.class))).thenReturn(null);

        // WHEN
        //THEN
        mockMvc.perform(delete("/medicalReport")
                .content(asJsonString(new MedicalRecord(firstNameConst,lastNameConst,birthdateConst,allergiesListConst,medicationsListConst)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}
