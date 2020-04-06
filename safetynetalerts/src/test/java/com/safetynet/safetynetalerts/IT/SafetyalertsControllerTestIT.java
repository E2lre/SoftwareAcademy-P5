package com.safetynet.safetynetalerts.IT;


import com.safetynet.safetynetalerts.dao.FirestationDao;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

/*    @Before
    public void setUp() {

    }*/

    @Test
    public void SafetyalertsController_anExistingStationIsAsk_theStationAskIsSended_IT() throws Exception {

        //GIVEN : Give an exiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/Firestation?stationNumber=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("3"))
                .andExpect(jsonPath("$.address").value("748 Townings Dr"));

    }
    @Test
    public void SafetyalertsController_anInexistingStationIsAsk_errorIsSended_IT() throws Exception {

        //GIVEN : Give an inexiting firestation

        //WHEN //THEN return the station
        this.mockMvc.perform(get("/Firestation?stationNumber=0"))
                .andExpect(status().isNotFound());
    }
    @After
    public  void downUp(){
        this.mockMvc = null;
    }
}
