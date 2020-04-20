package com.safetynet.safetynetalerts.it;

import com.safetynet.safetynetalerts.model.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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


    /*---------------------------------------- GET -------------------------------*/
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
    public void PersonController_getInexistingPerson_errorIsSended() throws Exception {

        //GIVEN : Give an inexisting address


        //WHEN //THEN return notfound
        mockMvc.perform(get("/childAlert?address="+incorrectAddressConst))
                .andExpect(status().isNotFound())
        ;
    }

}
