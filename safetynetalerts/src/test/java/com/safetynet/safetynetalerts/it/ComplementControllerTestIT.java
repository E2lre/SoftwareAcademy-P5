package com.safetynet.safetynetalerts.it;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ComplementControllerTestIT {
    @Autowired
    private MockMvc mockMvc;
    //Test constantes
    String addressConst = "1509 Culver St";
    String incorrectAddressConst = "10 Downing Str";
    String firstNameConst = "Roger";
    String lastNameConst = "Boyd";
    String cityConst = "Culver";
    String inexistingFirstNameConst = "Harry";
    String inexistingLastNameConst = "Potter";
    String inexistingCityConst = "Groville";

    /*---------------------------------------- GET Fire-------------------------------*/
    @Test
    public void getFireListByAddress_getAnExistingAdressWithFire_theListOfFireAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting address

        //WHEN //THEN return tist of child
        mockMvc.perform(get("/fire?address="+addressConst))
                .andExpect(status().isOk());
//TODO verifier liste non null
    }

    /**
     * Controler : person
     * Test : Get : try to get an inexisting address
     * @throws Exception
     */
    @Test
    public void  getFireListByAddress_getInexistingAddress_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting address

        //WHEN //THEN return notfound
        mockMvc.perform(get("/fire?address="+incorrectAddressConst))
                .andExpect(status().isNotFound());
    }

    /*---------------------------------------- GET Flood-------------------------------*/
    @Test
    public void getFloodByStationList_getAnExistingStationListWithFloof_theListOfFloodAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting stations

        //WHEN //THEN return list of child
        mockMvc.perform(get("/flood?stations=1,2"))
                .andExpect(status().isOk());

    }

    /**
     * Controler : person
     * Test : Get : try to get an inexisting address
     * @throws Exception
     */
    @Test
    public void  getFloodByStationList_getInexistingstationList_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting station

        //WHEN //THEN return notfound
        mockMvc.perform(get("/flood?stations=0"))
                .andExpect(status().isNotFound());
    }
    /*---------------------------------------- GET getPersonInfoByFisrtNameLastName-------------------------------*/
    @Test
    public void getPersonInfoByFisrtNameLastName_getAnExistingFirstNaneLastName_theListOfPersonInfoAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting person

        //WHEN //THEN return list of PersonInfo
        mockMvc.perform(get("/personInfo?firstName="+firstNameConst+"&lastName="+lastNameConst))
                .andExpect(status().isOk());

    }


    @Test
    public void  getPersonInfoByFisrtNameLastName_getInexistingFirstNameLastName_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting person

        //WHEN //THEN return notfound
        mockMvc.perform(get("/personInfo?firstName="+inexistingFirstNameConst+"&lastName="+inexistingLastNameConst))
                .andExpect(status().isNotFound());
    }
    /*---------------------------------------- GET getEmailByCity-------------------------------*/
    @Test
    public void getEmailByCity_getAnExistingCity_theListOfEmailAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting City

        //WHEN //THEN return list of PersonInfo
        mockMvc.perform(get("/communityEmail?city="+cityConst))
                .andExpect(status().isOk());

    }


    @Test
    public void  getEmailByCity_getInexistingCity_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting city

        //WHEN //THEN return notfound
        mockMvc.perform(get("/communityEmail?city="+inexistingCityConst))
                .andExpect(status().isNotFound());
    }
}
