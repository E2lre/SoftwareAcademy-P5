package com.safetynet.safetynetalerts.ut.medicalrecord;

import com.safetynet.safetynetalerts.dao.MedicalRecordDao;
import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.service.InputDataReader;
import com.safetynet.safetynetalerts.service.medicalrecord.MedicalRecordService;
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
public class MedicalRecordServiceTest {
    @Autowired
    private MedicalRecordService medicalRecordService;

    @MockBean
    private MedicalRecordDao medicalRecordDao;

    @MockBean
    private InputDataReader inputDataReader;

    private MedicalRecord medicalRecord;

    //constantes de test
    String firstNameConst = "Bill";
    String lastNameConst = "Gates";
    String birthdateConst = "01/01/1970";
    List<String> medicationsListConst;
    List<String> allergiesListConst;
    String lastNameInexistingConst = "Invisible Man";
    @BeforeEach
    public void setUpEach() {

        medicationsListConst = new ArrayList<>(Arrays.asList("hydroxychloroquine", "azithromycine"));
        allergiesListConst = new ArrayList<>(Arrays.asList("peanut", "shellfish"));


        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(firstNameConst);
        medicalRecord.setLastName(lastNameConst);
        medicalRecord.setBirthdate(birthdateConst);
        medicalRecord.setAllergies(allergiesListConst);
        medicalRecord.setMedications(medicationsListConst);
    }

    /*------------------------ Get ---------------------------------*/
    /**
     * MedicalRecordService
     * Test : Get en existing MedicalRecord
     */
    @Test
    public void get_existingFirstnameLastnameGiven_medicalInfoAreReturn(){
        //GIVEN
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(medicalRecord);
        //WHEN

        MedicalRecord medicalRecordServiceTest = medicalRecordService.get(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordServiceTest.getLastName()).isEqualTo(lastNameConst);
        assertThat(medicalRecordServiceTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordServiceTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordServiceTest.getMedications()).isEqualTo(medicationsListConst);

    }
    /**
     * MedicalRecordService
     * Test : Get en INexisting MedicalRecord
     */
    @Test
    public void get_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(medicalRecordDao.get(any(MedicalRecord.class))).thenReturn(null);
        //WHEN
        MedicalRecord medicalRecordServiceTest = medicalRecordService.get(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest).isNull();
    }
    /*------------------------ Add ---------------------------------*/
    /**
     * MedicalRecordService
     * Test : add en INexisting medicalrecord
     */
    @Test
    public void add_inexistingFirstnameLastnameGiven_medicalInfoIsReturn() {

        //GIVEN
        Mockito.when(medicalRecordDao.add(any(MedicalRecord.class))).thenReturn(medicalRecord);
        //WHEN
        MedicalRecord medicalRecordServiceTest = medicalRecordService.add(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordServiceTest.getLastName()).isEqualTo(lastNameInexistingConst);
        assertThat(medicalRecordServiceTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordServiceTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordServiceTest.getMedications()).isEqualTo(medicationsListConst);
    }

    /**
     * MedicalRecordService
     * Test : add en existing medicalrecord
     */
    @Test
    public void add_existingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(medicalRecordDao.add(any(MedicalRecord.class))).thenReturn(null);
        //WHEN
        MedicalRecord medicalRecordServiceTest = medicalRecordService.add(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest).isNull();
    }
    /*------------------------ Update ---------------------------------*/
    /**
     * MedicalRecordService
     * Test : update en existing medicalrecord
     */
    @Test
    public void update_existingFirstnameLastnameGiven_medicalInfoIsReturn() {

        medicalRecord.setBirthdate("01/01/1980");
        medicationsListConst.add("acide acétylsalicylique");
        medicalRecord.setMedications(medicationsListConst);

        Mockito.when(medicalRecordDao.update(any(MedicalRecord.class))).thenReturn(medicalRecord);


        //WHEN
        MedicalRecord medicalRecordServiceTest = medicalRecordService.update(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest.getFirstName()).isEqualTo(firstNameConst);
        assertThat(medicalRecordServiceTest.getLastName()).isEqualTo(lastNameInexistingConst);
        assertThat(medicalRecordServiceTest.getBirthdate()).isEqualTo(birthdateConst);
        assertThat(medicalRecordServiceTest.getAllergies()).isEqualTo(allergiesListConst);
        assertThat(medicalRecordServiceTest.getMedications()).isEqualTo(medicationsListConst);

    }

    /**
     * MedicalRecordService
     * Test : update an Inexisting medicalrecord
     */
    @Test
    public void update_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(medicalRecordDao.update(any(MedicalRecord.class))).thenReturn(null);

        //WHEN
        MedicalRecord medicalRecordServiceTest = medicalRecordService.update(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTest).isNull();
    }
    /*------------------------ Delete ---------------------------------*/
    /**
     * MedicalRecordService
     * Test : delete en existing person
     */
    @Test
    public void delete_existingFirstnameLastnameGiven_thePersonIsReturn() {
        //GIVEN
        List<MedicalRecord> medicalRecordList = new ArrayList<>();
        medicalRecordList.add(medicalRecord);
        Mockito.when(medicalRecordDao.delete(any(MedicalRecord.class))).thenReturn(medicalRecordList);
        //WHEN
        List<MedicalRecord> medicalRecordServiceTestList = medicalRecordService.delete(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTestList.size()).isEqualTo(1);

    }
    /**
     * MedicalRecordService
     * Test : delete an Inexisting person
     */
    @Test
    public void delete_inexistingFirstnameLastnameGiven_nullIsReturn() {
        //GIVEN
        Mockito.when(medicalRecordDao.delete(any(MedicalRecord.class))).thenReturn(null);
        //WHEN
        List<MedicalRecord> medicalRecordServiceTestList = medicalRecordService.delete(medicalRecord);
        //THEN
        assertThat(medicalRecordServiceTestList).isNull();
    }
}
