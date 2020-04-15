package com.safetynet.safetynetalerts.it;


import com.safetynet.safetynetalerts.model.Firestation;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTestIT {

    @Autowired
    private MockMvc mockMvc;


/*    @Before
    public void setUp() {

    }*/

    /**
     * Controler : firestation
     * Test : Get : show an existing address
     * @throws Exception
     */
    //@DisplayName("Test Spring @Autowired Integration for SafetyalertsController")
    //@Test //TODO A réactiver avec la règle : ?stationNumber= <station_number> : retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
    public void SafetyalertsController_anExistingStationIsAsk_theStationAskIsSended() throws Exception {

        //GIVEN : Give an exiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("3"))
                .andExpect(jsonPath("$.address").value("748 Townings Dr"));

    }

    /**
     * Controler : firestation
     * Test : Get : try to show an inexisting address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_anInexistingStationIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=0"))
                .andExpect(status().isNotFound());
    }

    /**
     * Controler : firestation
     * Test : Add an new address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_addANewFirestation_theNewFireStationAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an new firestation

        //WHEN : the station is created

        //THEN :
        mockMvc.perform(post("/firestation")
                .content(asJsonString(new Firestation("10 downing str","10")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("10"))
                .andExpect(jsonPath("$.address").value("10 downing str"));

    }

    /**
     * Controler : firestation
     * Test : Update : update an existing address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_updateAFirestationByExistingSAddress_firestationUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation

        //WHEN : the station is update

        //THEN :
        mockMvc.perform(put("/firestation")
                .content(asJsonString(new Firestation("748 Townings Dr","10")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.station").value("10"))
                .andExpect(jsonPath("$.address").value("748 Townings Dr"));

    }

    /**
     * Controler : firestation
     * Test : Update : try to update an inexisting address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_updateAFirestationByInexistingAddress_firestationNotUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation

        //WHEN : the station is update

        //THEN :
        mockMvc.perform(put("/firestation")
                .content(asJsonString(new Firestation("10 downing street","99")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }

    /**
     * Controler : firestation
     * Test : Delete : Delete an existing address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_deleteFirestationByAddress_firestationDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation
        String existingStation = "2";
        String existingAddress = "951 LoneTree Rd";

        //WHEN : the station is deleted
        //THEN : return the station deleted

        mockMvc.perform(delete("/firestation")
                .content(asJsonString(new Firestation(existingAddress,existingStation)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        //TODO implémeter la lecture de la liste dans le test
    /*               .andExpect(jsonPath("$.station").value(existingStation))
                .andExpect(jsonPath("$.address").value(existingAddress));
*/
    }

    /**
     * Controler : firestation
     * Test : Delete : Try to delete an inexisting address
     * @throws Exception
     */
    @Test
    public void SafetyalertsController_deleteFirestationWithBadAddress_firestationNotDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give an inexisting firestation

        String stationToDelete = "99";
        String addressToDelete = "100 Downing Str";


        //WHEN : delete an inexisting station
        //THEN : return an error

        mockMvc.perform(delete("/firestation")
                .content(asJsonString(new Firestation(addressToDelete,stationToDelete)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

    }


    @After
    public  void downUp(){
        this.mockMvc = null;
    }
}
