package com.safetynet.safetynetalerts.service.medicalrecord;

import com.safetynet.safetynetalerts.model.MedicalRecord;

import java.util.List;

public interface MedicalRecordService {
    public MedicalRecord get (MedicalRecord medicalRecord);
    public MedicalRecord add (MedicalRecord medicalRecord);
    public MedicalRecord update (MedicalRecord medicalRecord);
    public List<MedicalRecord> delete (MedicalRecord medicalRecord);
}
