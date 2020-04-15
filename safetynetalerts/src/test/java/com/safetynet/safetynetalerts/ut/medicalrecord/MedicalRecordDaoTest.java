package com.safetynet.safetynetalerts.ut.medicalrecord;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;
import com.safetynet.safetynetalerts.service.InputDataReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class MedicalRecordDaoTest {
    @Autowired
    private MedicalRecordDao medicalRecordDao;
    //@MockBean
    private MedicalRecord medicalRecord;
    @MockBean
    private MedicalRecord resultMedicalRecord;
    @MockBean
    private InputDataReader inputDataReader;

    //constantes de test
    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;
    String lastNameInexistingConst = "Invisible Man";
    @BeforeEach
    public void setUpEach() {

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine","azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut","shellfish"));



        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameConst);
        medicalRecord.setLastName(lastNameConst);
        medicalRecord.setBirthdate(birthdateConst);
        medicalRecord.setAllergies(allergiesListConst);
        medicalRecord.setMedications(medicationsListConst);

        Mockito.when(resultMedicalRecord.getFirstName()).thenReturn(firstNameConst);
        Mockito.when(resultMedicalRecord.getLastName()).thenReturn(lastNameConst);
        Mockito.when(resultMedicalRecord.getBirthdate()).thenReturn(birthdateConst);
        Mockito.when(resultMedicalRecord.getAllergies()).thenReturn(allergiesListConst);
        Mockito.when(resultMedicalRecord.getMedications()).thenReturn(medicationsListConst);


    }

    /*------------------------ Get ---------------------------------*/
    /**
     * MedicalRecordDao
     * Test : Get en existing MedicalRecord
     */
    @Test
    public void get_existingFirstnameLastnameGiven_medicalInfoAreReturn(){

        MedicalRecord medicalRecordDaoTest = medicalRecordDao.get(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordDaoTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(medicalRecordDaoTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordDaoTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordDaoTest.getMedications()).isEqualTo(medicationsListConst);

    }
    /**
     * MedicalRecordDao
     * Test : Get en INexisting MedicalRecord
     */
    @Test
    public void get_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        medicalRecord.setLastName(lastNameInexistingConst);
        //WHEN
        MedicalRecord medicalRecordDaoTest = medicalRecordDao.get(medicalRecord);
        //THEN
        assertThat(medicalRecordDao).isNull();
    }

    /*------------------------ Add ---------------------------------*/
    /**
     * MedicalRecordDao
     * Test : add en INexisting medicalrecord
     */
    @Test
    public void add_inexistingFirstnameLastnameGiven_medicalInfoIsReturn() {
        //GIVEN
        medicalRecord.setLastName(lastNameInexistingConst);
        //WHEN
        MedicalRecord medicalRecordDaoTest = medicalRecordDao.add(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordDaoTest.getLastName()).isEqualTo(lastNameInexistingConst);
        assertThat(medicalRecordDaoTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordDaoTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordDaoTest.getMedications()).isEqualTo(medicationsListConst);
    }

    /**
     * MedicalRecordDao
     * Test : add en existing medicalrecord
     */
    @Test
    public void add_existingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        medicalRecord.setLastName(lastNameConst);
        //WHEN
        MedicalRecord medicalRecordDaoTest = medicalRecordDao.add(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest).isNull();
    }

    /*------------------------ Update ---------------------------------*/
    /**
     * MedicalRecordDao
     * Test : update en existing medicalrecord
     */
    @Test
    public void update_existingFirstnameLastnameGiven_medicalInfoIsReturn() {
        //GIVEN
        medicationsListConst.add("acide acétylsalicylique");
        medicalRecord.setMedications(medicationsListConst);

        //WHEN
        MedicalRecord medicalRecordDaoTest = medicalRecordDao.update(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordDaoTest.getLastName()).isEqualTo(lastNameInexistingConst);
        assertThat(medicalRecordDaoTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordDaoTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordDaoTest.getMedications()).isEqualTo(medicationsListConst);

    }

    /**
     * MedicalRecordDao
     * Test : update an Inexisting medicalrecord
     */
    @Test
    public void update_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        medicalRecord.setLastName(lastNameInexistingConst);
        //WHEN
        MedicalRecord medicalRecordDaoTest = medicalRecordDao.update(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest).isNull();
    }

    /*------------------------ Delete ---------------------------------*/
    /**
     * MedicalRecordDao
     * Test : delete en existing medicalrecord
     */
    @Test
    public void delete_existingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        //WHEN
        List<MedicalRecord> medicalRecordDaoTest = medicalRecordDao.delete(medicalRecord);
        //THEN
        assertThat(medicalRecordDaoTest.size()).isEqualTo(1);

    }
    /**
     * MedicalRecordDao
     * Test : delete an Inexisting medicalrecord
     */
    @Test
    public void delete_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        medicalRecord.setLastName(lastNameInexistingConst);
        //WHEN
        List<MedicalRecord> medicalRecordDaoTest = medicalRecordDao.delete(medicalRecord);
       //THEN
        assertThat(medicalRecordDaoTest).isNull();
    }

    /*------------------------ Load ---------------------------------*/

    /**
     * PersonDAO
     * Test : load a medicalrecordlist
     */
    @Test
    public void load_medicalrecordListGiven_trueIsReturn() {
        //GIVEN
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);
        //WHEN
        boolean medicalRecordLoadResult = medicalRecordDao.load(medicalRecordList);
        //THEN
        assertThat(medicalRecordLoadResult).isTrue();


    }
}
