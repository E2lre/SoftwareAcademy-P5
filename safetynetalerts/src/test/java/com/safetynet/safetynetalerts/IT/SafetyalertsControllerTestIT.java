package com.safetynet.safetynetalerts.IT;


import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.web.controller.SafetyalertsController;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.safetynet.safetynetalerts.UT.SafetyalertsControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SafetyalertsControllerTestIT {

    //@Autowired
    //  private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    // @MockBean
    @Autowired
    private FirestationDao firestationDao;

    //@Autowired
    private Firestation firestation;

/*    @Before
    public void setUp() {

    }*/

    @DisplayName("Test Spring @Autowired Integration for SafetyalertsController")
    @Test
    public void SafetyalertsController_anExistingStationIsAsk_theStationAskIsSended() throws Exception {

        //GIVEN : Give an exiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("3"))
                .andExpect(jsonPath("$.address").value("748 Townings Dr"));

    }
    @Test
    public void SafetyalertsController_anInexistingStationIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=0"))
                .andExpect(status().isNotFound());
    }

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


        @After
    public  void downUp(){
        this.mockMvc = null;
    }
}
