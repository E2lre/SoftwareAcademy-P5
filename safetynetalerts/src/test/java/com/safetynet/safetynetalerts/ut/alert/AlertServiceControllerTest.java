package com.safetynet.safetynetalerts.ut.alert;

import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Child;
import com.safetynet.safetynetalerts.model.detail.Childs;
import com.safetynet.safetynetalerts.model.detail.Phone;
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

import java.lang.reflect.Array;
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
    String firstNameConst = "Lily";
    String lastNameConst = "Potter";
    String addressConst = "4, Privet Drive";
    String cityConst = "Little Whinging";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "harry.potter@poudlard.com";
    String firstNameChildConst = "Harry";
    String lastNameChildConst = "Potter";
    String ageChildConst = "12";


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

    /*------------------------ Get ChildAlert---------------------------------*/

    /**
     * Test : Controler alertInfo
     * Get : get child list at existing address person
     * @throws Exception
     */
    @Test
    public void childAlertController_getAnExistingAdressWithChild_theListOfChildAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give a child to get
        List<Child> childList= new ArrayList<>();
        childList.add(new Child(firstNameChildConst,lastNameChildConst,ageChildConst));
        List<Person> personList = new ArrayList<>();
        personList.add(personMock);
        Mockito.when(alertService.getChildByAddress((anyString()))).thenReturn(new Childs(childList,personList));

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
        List<Child> childList= null;
        List<Person> personList = null;
        Mockito.when(alertService.getChildByAddress((anyString()))).thenReturn(new Childs(childList,personList));

        //WHEN //THEN return the person added
        mockMvc.perform(get("/childAlert?address=No child at this address"))
                .andExpect(status().isNotFound());
    }
    /*------------------------ Get phoneAlert---------------------------------*/

    /**
     * Test : Controler alertInfo
     * Get : get phone list at existing address person
     * @throws Exception
     */
    @Test
    public void phoneAlertController_getAnExistingStationWithPhone_theListOfPhonendHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give a child to get
        List<Phone> phoneList = new ArrayList<>();
        phoneList.add(new Phone(personMock.getFirstName(),personMock.getLastName(),personMock.getPhone()));
        Mockito.when(alertService.getPhoneByStation((anyString()))).thenReturn(phoneList);

        //WHEN //THEN return the person added
        mockMvc.perform(get("/phoneAlert?station=2"))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler alertInfo
     * Get : get phone list at inexisting address person
     * @throws Exception
     */
    @Test
    public void phoneAlertController_getAnExistingStationWithNoPhone_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a person to add
        Mockito.when(alertService.getPhoneByStation((anyString()))).thenReturn(null);

        //WHEN //THEN return the person added
        mockMvc.perform(get("/phoneAlert?station=99"))
                .andExpect(status().isNotFound());
    }
}
