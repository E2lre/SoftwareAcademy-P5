package com.safetynet.safetynetalerts.UT;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.dao.FirestationDaoImpl;
import com.safetynet.safetynetalerts.model.Firestation;
import com.safetynet.safetynetalerts.service.InputDataReader;
import org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



/*@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc*/
@SpringBootTest
public class FirestationDaoTest {
    @TestConfiguration
    static class FirestationDaoImplTestContextConfiguration {
        @Bean
        public FirestationDao firestationDao () {
            return new FirestationDaoImpl();
        }
    }

    @Autowired
    private FirestationDao firestationDao;
    //@Autowired
    private Firestation fireststationTest;
    @MockBean
    private Firestation firestation;
    //@MockBean
    //public static List<Firestation> firestations;
//    = new ArrayList<>();
    @MockBean
    private InputDataReader inputDataReader;

    String correctAddress = "748 Townings Dr";
    String correctStation = "3";


    @BeforeEach
    private void setUpPerTest() {

/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
*/
        fireststationTest = new Firestation();
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
/*
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));
*/

    }


    @AfterEach
    private void cleanUpEach() {
    //    firestationDao = null;
    //    fireststationTest = null;
    }

    @Test
    public void save_oneStationToAdd_theStationAddedIsReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        //WHEN
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));

        //THEN
        List<Firestation>  fireststations = firestationDao.findAll();
        assertThat(fireststations.size()).isEqualTo(1) ;
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }


    @Test
    public void findByStation_aStationExist_theStationIsReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.save(new Firestation(correctAddress, correctStation));
        fireststationTest = firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.findByStation("3");

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo(firestation.getStation());
        assertThat(fireststationTest.getAddress()).isEqualTo(firestation.getAddress());
    }
    @Test
    public void findByStation_aStationNotExist_nullIsReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        //Firestation fireststationTest = new Firestation();
        fireststationTest = firestationDao.findByStation("5");

        //THEN
        assertThat(fireststationTest).isNull();
    }
    @Test
    public void findAll_twoStationAdd_twoStationMoreAreReturn() {

        //GIVEN
/*
        String correctAddress = "748 Townings Dr";
        String correctStation = "3";
        Mockito.when(firestation.getStation()).thenReturn(correctStation);
        Mockito.when(firestation.getAddress()).thenReturn(correctAddress);
        Mockito.when(firestation.toString()).thenReturn("Firestation [address="+correctAddress+", station="+correctStation+"]");
*/
        List<Firestation>  fireststations = firestationDao.findAll();
        int nbFirestationStart = fireststations.size();
        firestationDao.save(new Firestation(correctAddress, correctStation));
        firestationDao.save(new Firestation(new String ("1509 Culver St"), "4"));


        //WHEN
        fireststations = firestationDao.findAll();
        int nbFirestationFinal = fireststations.size();

        //THEN
        assertThat(nbFirestationFinal-nbFirestationStart).isEqualTo(2) ;
    }


}
