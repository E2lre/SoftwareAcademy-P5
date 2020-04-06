package com.safetynet.safetynetalerts.UT;


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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @MockBean
    private FirestationDao firestationDaoMock;
    @MockBean
    private Firestation firestationMock;
    @MockBean
    private InputDataReader inputDataReaderMock;

/*    @Before
    public void setUp() {

    }*/

    @Test
    public void SafetyalertsController_anExistingStationIsAsk_theStationAskIsSended() throws Exception {
        //firestation = new Firestation();
        //firestation.setStation("3");
        //firestation.setAddress("748 Townings Dr");
       //Mockito.when(firestationDao.findByStation(anyString())).thenReturn(firestation);
      //  Mockito.when(firestation.getStation()).thenReturn("3");
        //Mockito.when(firestation.getAddress()).thenReturn("748 Townings Dr");
       //Mockito.when(firestationDao.findByStation(anyString())).thenReturn(firestation);
       // Mockito.when(firestationDao.findByStation(anyString())).thenReturn(new Firestation("748 Townings Dr","3"));
//        Mockito.when(firestation(anyString(),anyString())).thenReturn("Firestation [address=748 Townings Dr, station=3]");
  //      Mockito.when(firestationDao.findByStation(anyString())).thenReturn(firestation);
//        Firestation spyFirestation = Mockito.spy(Firestation.class);
//        spyFirestation.setStation("3");
//        spyFirestation.setAddress("748 Townings Dr");
//        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(spyFirestation);

/*        firestationMock = new Firestation(); //Fonctionne mais passe par un niveau inférieur
        firestationMock.setStation("3");
        firestationMock.setAddress("748 Townings Dr");
        Mockito.when(firestationDaoMock.findByStation(anyString())).thenReturn(firestationMock);
        */

       /* Mockito.when(firestationMock).thenReturn(Firestation.class);
        Mockito.when(firestationDaoMock.findByStation(anyString())).thenReturn(any(Firestation.class));*/
       //GIVEN : Give a firestation
        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation("3");
        firestationMock.setAddress("748 Townings Dr");
        Mockito.when(firestationDaoMock.findByStation(anyString())).thenReturn(firestationMock);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/Firestation?stationNumber=3"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.station").value("3"))
                        .andExpect(jsonPath("$.address").value("748 Townings Dr"));
  }
    @Test
    public void SafetyalertsController_anInexistingStationIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexiting firestation
        //firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
       // firestationMock.setStation("3");
        //firestationMock.setAddress("748 Townings Dr");
        Mockito.when(firestationDaoMock.findByStation(anyString())).thenReturn(null);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/Firestation?stationNumber=3"))
                .andExpect(status().isNotFound());
    }
    @After
    public  void downUp(){
        this.mockMvc = null;
    }


}
