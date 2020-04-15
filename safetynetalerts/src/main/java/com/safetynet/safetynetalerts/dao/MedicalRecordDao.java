package com.safetynet.safetynetalerts.dao;

import com.safetynet.safetynetalerts.model.MedicalRecord;
import com.safetynet.safetynetalerts.model.Person;

import java.util.List;

public interface MedicalRecordDao {
    public MedicalRecord get (MedicalRecord medicalRecord);
    public MedicalRecord add (MedicalRecord medicalRecord);
    public MedicalRecord update (MedicalRecord medicalRecord);
    public List<MedicalRecord> delete (MedicalRecord medicalRecord);
    public boolean load (List<MedicalRecord> medicalRecordList);
    public boolean clear();
}
