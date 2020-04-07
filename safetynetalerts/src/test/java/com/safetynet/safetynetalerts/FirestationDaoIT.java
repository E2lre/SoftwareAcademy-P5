package com.safetynet.safetynetalerts;

import com.safetynet.safetynetalerts.dao.FirestationDao;
import com.safetynet.safetynetalerts.model.Firestation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class FirestationDaoIT {
    @Autowired
    private FirestationDao firestationDao;

    @Test
    public void findByStation_aStationExist_theStationIsReturn_IT() {

        //GIVEN
        Firestation fireststationTest = new Firestation();
        //WHEN
       fireststationTest = firestationDao.findByStation("4");

        //THEN
        assertThat(fireststationTest.getStation()).isEqualTo("4");
        assertThat(fireststationTest.getAddress()).isEqualTo("112 Steppes Pl");

    }
}
