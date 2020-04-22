package com.safetynet.safetynetalerts.ut.firestation;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.StationNumberInfo;
import com.safetynet.safetynetalerts.service.InputDataReader;

import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FirestationService firestationService;
    @MockBean
    private Firestation firestationMock;
    @MockBean
    private Person personMock;
    @MockBean
    private InputDataReader inputDataReaderMock;



    /*------------------------ Get ---------------------------------*/
    /**
     * Controler : firestation
     * Test : Get : show person list for a station number
     * @throws Exception
     */
    @Test
    public void FirestationController_anExistingStationIsAsk_personListIsSended() throws Exception {
        //GIVEN : Give a Person list
        //constantes de test
        String firstNameConst = "Bill";
        String lastNameConst = "Gates";
        String addressConst = "Somewhere in US";
        String cityConst = "a US City";
        String zipConst = "12345";
        String phoneConst = "0123456789";
        String emailConst = "bill.gates@microsoft.com";
        personMock = new Person(firstNameConst,lastNameConst,addressConst,cityConst,zipConst,phoneConst,emailConst);
        List<Person> personList = new ArrayList<>();
        personList.add(personMock);

        StationNumberInfo stationNumberInfo = new StationNumberInfo("1","0",personList);

        Mockito.when(firestationService.getPersonByStation(anyString())).thenReturn(stationNumberInfo);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                        .andExpect(status().isOk());
  }

    /**
     * Controler : firestation
     * Test : Get : try to show an inexisting address
     * @throws Exception
     */
    @Test
    public void FirestationController_anInexistingStationIsAsk_errorIsSended() throws Exception {

        //GIVEN : Give an inexiting firestation
        List<Person> personList = new ArrayList<>();
        StationNumberInfo stationNumberInfo = new StationNumberInfo();
        Mockito.when(firestationService.getPersonByStation(anyString())).thenReturn(stationNumberInfo);
        //WHEN //THEN return the station
        this.mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isNotFound());
    }

    /*------------------------ POST ---------------------------------*/
    /**
     * Controler : firestation
     * Test : Add an new address
     * @throws Exception
     */
    @Test
    public void FirestationController_addANewFirestation_theNewFireStationAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give a firestation
        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation("10");
        firestationMock.setAddress("10 downing str");
        Mockito.when(firestationService.save(any(Firestation.class))).thenReturn(firestationMock);
        //WHEN //THEN return the station added
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
    public void FirestationController_updateAFirestationByExistingAddress_firestationUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation
        String existingStation = "3";
        String existingAddress = "748 Townings Dr";

        String updateStation = "2";
        String updateAddress = existingAddress;

        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation(updateStation);
        firestationMock.setAddress(existingAddress);
        Mockito.when(firestationService.updateByAddress(any(Firestation.class))).thenReturn(firestationMock);
        //WHEN : the station is update
        //THEN : return the station updates

        mockMvc.perform(put("/firestation")
                .content(asJsonString(new Firestation(updateAddress,updateStation)))
//                .content(asJsonString(new Firestation("748 Townings Dr","3")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.station").value(updateStation))
                .andExpect(jsonPath("$.address").value(updateAddress));

    }
    /*------------------------ Put ---------------------------------*/
    /**
     * Controler : firestation
     * Test : Update : try to update an inexisting address
     * @throws Exception
     */
    @Test
    public void FirestationController_updateAFirestationByInexistingAddress_firestationNotUpdatedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation
        String existingStation = "3";
        String existingAddress = "748 Townings Dr";

        String updateStation = "2";
        String updateAddress = "10 Downing str";

        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation(existingStation);
        firestationMock.setAddress(existingAddress);
        Mockito.when(firestationService.updateByAddress(any(Firestation.class))).thenReturn(null);
        //WHEN : the station is update whith incorrect address
        //THEN : return the station updates

         mockMvc.perform(put("/firestation")
//                .content(asJsonString(new Firestation(updateAddress,updateStation)))
                 .content(asJsonString(new Firestation("748 Townings Dr","10")))
                 .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotModified());

    }
    /*------------------------ Delete ---------------------------------*/
    /**
     * Controler : firestation
     * Test : Delete : Delete an existing address
     * @throws Exception
     */
    @Test
    public void FirestationController_deleteFirestationByAddress_firestationDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation
        String existingStation = "2";
        String existingAddress = "951 LoneTree Rd";


        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation(existingAddress);
        firestationMock.setAddress(existingAddress);

        List<Firestation> firestationMockList = new ArrayList<>();
        firestationMockList.add(firestationMock);
        Mockito.when(firestationService.delete(any(Firestation.class))).thenReturn(firestationMockList);
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
    public void FirestationController_deleteFirestationWithBAdAddress_firestationNotDeletedAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give  existing firestation
        String existingStation = "3";
        String existingAddress = "748 Townings Dr";
        String stationToDelete = "3";
        String addressToDelete = "10 Downing Str";

        firestationMock = new Firestation(); //Fonctionne mais active un niveau inférieur
        firestationMock.setStation(existingAddress);
        firestationMock.setAddress(existingAddress);

        List<Firestation> firestationMockList = new ArrayList<>();
        firestationMockList.add(firestationMock);
        Mockito.when(firestationService.delete(any(Firestation.class))).thenReturn(null);
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
