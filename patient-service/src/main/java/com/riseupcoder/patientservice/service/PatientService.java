package com.riseupcoder.patientservice.service;

import com.riseupcoder.patientservice.dto.request.CreatePatientRequest;
import com.riseupcoder.patientservice.dto.request.UpdatePatientRequest;
import com.riseupcoder.patientservice.dto.response.PatientPageResponse;
import com.riseupcoder.patientservice.dto.response.PatientResponse;

import java.util.UUID;

public interface PatientService {

    PatientPageResponse getPatients(int page);

    PatientResponse createPatient(CreatePatientRequest createPatientRequest);

    PatientResponse updatePatient(UUID patientId, UpdatePatientRequest updatePatientRequest);

    void deletePatient(UUID patientId);
}
