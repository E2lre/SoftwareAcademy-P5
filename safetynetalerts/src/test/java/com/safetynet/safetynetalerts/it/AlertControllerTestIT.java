package com.safetynet.safetynetalerts.it;

import com.safetynet.safetynetalerts.model.Person;
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
public class AlertControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    //Test constantes
    String addressConst = "1509 Culver St";
    String incorrectAddressConst = "10 Downing Str";


    /*---------------------------------------- GET Child-------------------------------*/
    @Test
    public void childAlertController_getAnExistingAdressWithChild_theListOfChildAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting address

        //WHEN //THEN return tist of child
        mockMvc.perform(get("/childAlert?address="+addressConst))
                .andExpect(status().isOk());
//TODO verifier liste non null
    }

    /**
     * Controler : person
     * Test : Get : try to get an inexisting address
     * @throws Exception
     */
    @Test
    public void childAlertController_getInexistingPerson_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting address


        //WHEN //THEN return notfound
        mockMvc.perform(get("/childAlert?address="+incorrectAddressConst))
                .andExpect(status().isNotFound());
    }


    /*---------------------------------------- GET Phone-------------------------------*/
    @Test
    public void phoneAlertController_getAnExistingStationWithChild_theListOfPhoneAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give an exiting station

        //WHEN //THEN return list of phone
        mockMvc.perform(get("/phoneAlert?station=2"))
                .andExpect(status().isOk());
//TODO verifier liste non null
    }

    /**
     * Controler : person
     * Test : Get : try to get an inexisting address
     * @throws Exception
     */
    @Test
    public void phoneAlertController_getInexistingDtation_anHTTPErrorIsReturn() throws Exception {

        //GIVEN : Give an inexisting station


        //WHEN //THEN return notfound
        mockMvc.perform(get("/phoneAlert?station=99"))
                .andExpect(status().isNotFound());
    }

}
