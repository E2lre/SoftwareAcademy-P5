package com.safetynet.safetynetalerts.ut.complement;

import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.model.detail.Email;
import com.safetynet.safetynetalerts.model.detail.Fire;
import com.safetynet.safetynetalerts.model.detail.Flood;
import com.safetynet.safetynetalerts.model.detail.PersonInfo;
import com.safetynet.safetynetalerts.service.complement.ComplementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ComplementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ComplementService complementService;

    private Firestation firestation;
    private Person person;
    private MedicalRecord medicalRecord;
    //constantes de test

    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String addressConst = "Somewhere in US";
    String cityConst = "a US City";
    String zipConst = "12345";
    String phoneConst = "0123456789";
    String emailConst = "bill.gates@microsoft.com";

    String existingAddressConst = addressConst;
    String inexistingAddressConst = "pouldlar land";

    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;

    @BeforeEach
    private void setUpPerTest() {

        firestation = new Firestation(addressConst,"2");

        person = new Person();
        person.setFirstName(firstNameConst);
        person.setLastName(lastNameConst);
        person.setAddress(addressConst);
        person.setCity(cityConst);
        person.setZip(zipConst);
        person.setPhone(phoneConst);
        person.setEmail(emailConst);

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));


        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameConst);
        medicalRecord.setLastName(lastNameConst);
        medicalRecord.setBirthdate(birthdateConst);
        medicalRecord.setAllergies(allergiesListConst);
        medicalRecord.setMedications(medicationsListConst);

    }

    /*------------------------ Get FireList---------------------------------*/

    /**
     * Test : Controler getFireListByAddress
     * Get : get fire list at existing address
     * @throws Exception
     */
    @Test
    public void getFireListByAddress_getAnExistingAdressWithFire_theListOfFireAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a Fire to get
        List<Fire> fireList= new ArrayList<>();
        fireList.add(new Fire("2",firstNameConst,lastNameConst,phoneConst,"25",medicationsListConst,allergiesListConst));
        List<Person> personList = new ArrayList<>();
        Mockito.when(complementService.getFireByAddress(anyString())).thenReturn(fireList);

        //WHEN //THEN fire is return
        mockMvc.perform(get("/fire?address="+addressConst))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler getFireListByAddress
     * Get : get Fire list at inexisting address
     * @throws Exception
     */
    @Test
    public void getFireListByAddress_getAnExistingAdressWithNoFire_HTTPErrorCodeIsReturn() throws Exception {


        //GIVEN : Give a Fire to get
        List<Fire> fireList= new ArrayList<>();
        Mockito.when(complementService.getFireByAddress(anyString())).thenReturn(fireList);

        //WHEN //THEN http error
        mockMvc.perform(get("/fire?address="+inexistingAddressConst))
                .andExpect(status().isNotFound());

    }

    /*------------------------ Get FloodList---------------------------------*/

    /**
     * Test : Controler getFloodByStationList
     * Get : get flood list at existing station list
     * @throws Exception
     */
    @Test
    public void getFloodByStationList_getAnExistingStationListWithFire_theListOfFloodAndHTTPCodeAreReturn() throws Exception {


        //GIVEN : Give a FloodList to get
        List<Fire> fireList= new ArrayList<>();
        fireList.add(new Fire("2",firstNameConst,lastNameConst,phoneConst,"25",medicationsListConst,allergiesListConst));
        List<Flood> floodList = new ArrayList<>();
        floodList.add(new Flood(addressConst,fireList));
        Mockito.when(complementService.getFloodByStationList(any(List.class))).thenReturn(floodList);

        //WHEN //THEN fire is return
        mockMvc.perform(get("/flood?stations=1"))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler getFloodByStationList
     * Get : get Flood list at inexisting station
     * @throws Exception
     */
    @Test
    public void getFloodByStationList_getAnInaxistingStation_HTTPErrorCodeIsReturn() throws Exception {

        //GIVEN : Give a flood to get
        List<Flood> floodList = new ArrayList<>();
        Mockito.when(complementService.getFloodByStationList(any(List.class))).thenReturn(floodList);

        //WHEN //THEN http error
        mockMvc.perform(get("/flood?stations=1"))
                .andExpect(status().isNotFound());

    }
    /*------------------------ Get PersonInfo---------------------------------*/

    /**
     * Test : Controler getPersonInfoByFisrtNameLastName
     * Get : get PersonInfo list at existing FirstName & LastName
     * @throws Exception
     */
    @Test
    public void getPersonInfoByFisrtNameLastName_getAnExistingFirstNameLastName_theListOfPErsonInfoAndHTTPCodeAreReturn() throws Exception {

        //GIVEN : Give a personInfoList to get
        List<PersonInfo> personInfoList = new ArrayList<>();
        personInfoList.add(new PersonInfo(firstNameConst,lastNameConst,addressConst,"25",emailConst,medicationsListConst,allergiesListConst));
        Mockito.when(complementService.getPersonInfoByFisrtNameLastName(anyString(),anyString())).thenReturn(personInfoList);

        //WHEN //THEN fire is return
        mockMvc.perform(get("/personInfo?firstName="+firstNameConst+"&lastName="+lastNameConst))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler getPersonInfoByFisrtNameLastName
     * Get : get PersonInfo list at inexisting FirstNAme & LastName
     * @throws Exception
     */
    @Test
    public void getPersonInfoByFisrtNameLastName_getAnInexistingFisrtNameLAstname_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a personInfoList to get
        List<PersonInfo> personInfoList = new ArrayList<>();
        Mockito.when(complementService.getPersonInfoByFisrtNameLastName(anyString(),anyString())).thenReturn(personInfoList);

        //WHEN //THEN fire is return
        mockMvc.perform(get("/personInfo?firstName="+firstNameConst+"&lastName="+lastNameConst))
                .andExpect(status().isNotFound());

    }

    /*------------------------ Get Email by city---------------------------------*/

    /**
     * Test : Controler getEmailByCity
     * Get : get Email list at existing city
     * @throws Exception
     */
    @Test
    public void getEmailByCity_getAnExistingCity_theListOfEMailListAndHTTPCodeAreReturn() throws Exception {
        //GIVEN : Give a City to get
        List<Email> emailList = new ArrayList<>();
        emailList.add(new Email(emailConst));
        Mockito.when(complementService.getEmailByCity(anyString())).thenReturn(emailList);

        //WHEN //THEN fire is return
        mockMvc.perform(get("/communityEmail?city="+cityConst))
                .andExpect(status().isOk());

    }

    /**
     * Test : Controler getEmailByCity
     * Get : get Email list at inexisting city
     * @throws Exception
     */
    @Test
    public void getEmailByCity_getAnInexistingCity_HTTPErrorCodeIsReturn() throws Exception {
        //GIVEN : Give a personInfoList to get
        List<Email> emailList = new ArrayList<>();
        Mockito.when(complementService.getEmailByCity(anyString())).thenReturn(emailList);
        //WHEN //THEN fire is return
        mockMvc.perform(get("/communityEmail?city="+cityConst))
                .andExpect(status().isNotFound());

    }
}
