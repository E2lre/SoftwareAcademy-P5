package com.safetynet.safetynetalerts.ut.alert;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.alert.AlertService;
import com.safetynet.safetynetalerts.service.person.PersonService;
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
import java.util.List;

import static com.safetynet.safetynetalerts.ut.firestation.FirestationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AlertServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlertService alertService;
    @MockBean
    private Person personMock;
    @MockBean
    private InputDataReader inputDataReaderMock;

    //constantes de test
    String firstNameConst = "Harry";
    String lastNameConst = "Potter";
    String addressConst = "4, Privet Drive";
    String cityConst = "Little Whinging";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "harry.potter@poudlard.com";

    @BeforeEach
    public void setUpEach() {

        personMock = new Person();
        personMock.setFirstName(firstNameConst);
        personMock.setLastName(lastNameConst);
        personMock.setAddress(addressConst);
        personMock.setCity(cityConst);
        personMock.setZip(zipConst);
        personMock.setPhone(phoneConst);
        personMock.setEmail(emailConst);

    }

    /*------------------------ Get ---------------------------------*/

    /**
     * Test : Controler alertInfo
     * Get : get child list at existing address person
     * @throws Exception
     */
    @Test
    public void childAlertController_getAnExistingAdressWithChild_theListOfChildAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a child to get
        List<Person> childList = new ArrayList<>();
        childList.add(personMock);

        Mockito.when(alertService.getChildByAddress((anyString()))).thenReturn(childList);
        //WHEN //THEN return the person added
        mockMvc.perform(get("/childAlert?address="+addressConst))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler alertInfo
     * Get : get child list at inexisting address person
     * @throws Exception
     */
    @Test
    public void childAlertController_getAnExistingAdressWithNoChild_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a person to add

        Mockito.when(alertService.getChildByAddress((anyString()))).thenReturn(null);
        //WHEN //THEN return the person added
        mockMvc.perform(get("/childAlert?address=No child at this address"))
                .andExpect(status().isNotFound());
    }


}
