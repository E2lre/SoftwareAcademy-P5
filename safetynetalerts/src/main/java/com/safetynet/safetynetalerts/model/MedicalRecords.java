package com.safetynet.safetynetalerts.model;

import java.util.List;

public class MedicalRecords {




    private List<MedicalRecord> medicalrecords = null;

    public MedicalRecords() {

    }
    public MedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalrecords = medicalRecords;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return medicalrecords;
    }

    public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        this.medicalrecords = medicalRecords;
    }
    @Override
    public String toString() {
        return "MedicalRecords{" +
                "medicalRecords=" + medicalrecords +
                '}';
    }

}
