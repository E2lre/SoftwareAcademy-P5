package com.safetynet.safetynetalerts.UT;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.InputDataReader;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringJUnit4ClassRunner.class)
//@WithMockUser
//@WebAppConfiguration
//@ContextConfiguration(classes = SafetyalertsController.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SafetyalertsControllerTest {
    //@Autowired
  //  private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

/*
    @MockBean
    private FirestationDao firestationDaoMock;
*/
    @MockBean
    private FirestationDao firestationDao;
    @MockBean
    private Firestation firestationMock;
    @MockBean
    private InputDataReader inputDataReaderMock;

/*    @Before
    public void setUp() {

    }*/

    @Test
    public void SafetyalertsController_anExistingStationIsAsk_theStationAskIsSended() throws Exception {



       //GIVEN : Give a firestation
        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation("3");
        firestationMock.setAddress("748 Townings Dr");
        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(firestationMock);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.station").value("3"))
                        .andExpect(jsonPath("$.address").value("748 Townings Dr"));
  }
    @Test
    public void SafetyalertsController_anInexistingStationIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexiting firestation

        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(null);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void SafetyalertsController_addANewFirestation_theNewFireStationAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give a firestation
        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation("10");
        firestationMock.setAddress("10 downing str");
        Mockito.when(firestationDao.save(any(Firestation.class))).thenReturn(firestationMock);
        //WHEN //THEN return the station added
         mockMvc.perform(post("/firestation")
                .content(asJsonString(new Firestation("10 downing str","10")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("10"))
                .andExpect(jsonPath("$.address").value("10 downing str"));
   /*     mockMvc.perform(MockMvcRequestBuilders
                .post("/firestation")
                .content(asJsonString(new Firestation("10 downing str","5")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.station").value("5"))
                .andExpect(jsonPath("$.address").value("10 downing str"));*/
    }
    @After
    public  void downUp(){
        this.mockMvc = null;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
