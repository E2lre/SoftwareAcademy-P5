package com.safetynet.safetynetalerts.UT;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.firestation.FirestationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class FirestationServiceTest {

    private Firestation fireststationTest;
    @Autowired
    private FirestationService firestationService;
    @MockBean
    private FirestationDao firestationDao;
    @MockBean
    private InputDataReader inputDataReader;

    String correctAddress;
    String correctStation;
    String incorrectAddress;
    String incorrectStation;


    @BeforeEach
    private void setUpPerTest() {
        correctAddress = "Correct Address";
        correctStation ="1";
        incorrectAddress = "Incorrect Address";
        incorrectStation ="99";
    }

    @Test
    public void findAll_twoStationAdd_twoStationMoreAreReturn() {
        //GIVEN
        List<Firestation>  firesstations = new ArrayList<>();
        firesstations.add(new Firestation(correctAddress, correctStation));
        firesstations.add(new Firestation(incorrectAddress, incorrectStation));
        Mockito.when(firestationDao.findAll()).thenReturn(firesstations);
        //WHEN
        List<Firestation> fireststatioList = firestationService.findAll();
        //THEN
        assertThat(fireststatioList.size()).isEqualTo(2);
    }

    @Test
    public void findByStation_aStationExist_theStationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.findByStation(correctStation);
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void findByStation_aStationNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.findByStation(anyString())).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.findByStation(incorrectStation);
        //THEN
        assertThat(fireststationTest).isNull();

    }
    @Test
    public void save_oneStationToAdd_theStationAddedIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.save(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.save(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }

    @Test
    public void save_oneStationToAddWithError_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.save(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.save(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest).isNull();


    }
    @Test
    public void updateByStation_aStationExist_firestationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByStation(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.updateByStation(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void updateByStation_aStationNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByStation(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.updateByStation(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(fireststationTest).isNull();

    }


    @Test
    public void updateByAddress_anAddressExist_firestationIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByAddress(any(Firestation.class))).thenReturn(new Firestation(correctAddress, correctStation));
        //WHEN
        fireststationTest = firestationService.updateByAddress(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(correctStation);
        assertThat(fireststationTest.getAddress()).isEqualTo(correctAddress);

    }
    @Test
    public void updateByAddress_anAddressNotExist_nullIsReturn() {
        //GIVEN

        Mockito.when(firestationDao.updateByAddress(any(Firestation.class))).thenReturn(null);
        //WHEN
        fireststationTest = firestationService.updateByAddress(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(fireststationTest).isNull();

    }
    @Test
    public void delete_anAddressExist_firestationIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        List<Firestation>  firestationsResult = new ArrayList<>();
        firestations.add(new Firestation(correctAddress, correctStation));


        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(firestations);
        //WHEN
        firestationsResult = firestationService.delete(new Firestation(correctAddress, correctStation));
        //THEN
        assertThat(firestationsResult.size()).isEqualTo(1);
        //TODO ajouter des conditions

    }
    @Test
    public void delete_anAddressNotExist_nullIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(null);
        //WHEN
        firestations = firestationService.delete(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(firestations).isNull();

    }
    @Test
    public void delete_addressNotExist_nullIsReturn() {
        //GIVEN
        List<Firestation>  firestations = new ArrayList<>();
        Mockito.when(firestationDao.delete(any(Firestation.class))).thenReturn(null);
        //WHEN
        firestations = firestationService.delete(new Firestation(incorrectAddress, incorrectStation));
        //THEN
        assertThat(firestations).isNull();

    }
}
