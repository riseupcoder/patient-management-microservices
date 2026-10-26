package com.riseupcoder.patientservice.controller;

import com.riseupcoder.patientservice.dto.request.CreatePatientRequest;
import com.riseupcoder.patientservice.dto.request.UpdatePatientRequest;
import com.riseupcoder.patientservice.dto.response.PatientPageResponse;
import com.riseupcoder.patientservice.dto.response.PatientResponse;
import com.riseupcoder.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public ResponseEntity<PatientPageResponse> getPatients(
            @RequestParam(defaultValue = "0") int page) {
        PatientPageResponse patientPageResponse = patientService.getPatients(page);

        return ResponseEntity.ok(patientPageResponse);
    }


    @PostMapping("/patients")
    public ResponseEntity<PatientResponse> createPatient(
            @Valid @RequestBody CreatePatientRequest request) {

        PatientResponse savedPatient = patientService.createPatient(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(
            @Valid @RequestBody UpdatePatientRequest request,
            @PathVariable UUID patientId
    ) {
        PatientResponse updatedPatient = patientService.updatePatient(patientId, request);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID patientId) {
        patientService.deletePatient(patientId);

        return ResponseEntity.noContent().build();
    }

}
