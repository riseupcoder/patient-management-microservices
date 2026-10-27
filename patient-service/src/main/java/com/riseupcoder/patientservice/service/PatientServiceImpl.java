package com.riseupcoder.patientservice.service;

import com.riseupcoder.patientservice.dto.request.CreatePatientRequest;
import com.riseupcoder.patientservice.dto.request.UpdatePatientRequest;
import com.riseupcoder.patientservice.dto.response.PatientPageResponse;
import com.riseupcoder.patientservice.dto.response.PatientResponse;
import com.riseupcoder.patientservice.exception.APIException;
import com.riseupcoder.patientservice.exception.ResourceNotFoundException;
import com.riseupcoder.patientservice.mapper.PatientMapper;
import com.riseupcoder.patientservice.model.Patient;
import com.riseupcoder.patientservice.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public PatientServiceImpl(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientPageResponse getPatients(int page) {

        if (page < 0) page = 0;

        Pageable pageRequest = PageRequest.of(page, 10);

        Page<Patient> patientPage = patientRepository.findAll(pageRequest);

        Page<PatientResponse> responsePage = patientPage.map(patientMapper::toDto);

        return PatientPageResponse.builder()
                                  .contents(responsePage.getContent())
                                  .page(responsePage.getNumber())
                                  .pageSize(responsePage.getSize())
                                  .totalElements(responsePage.getTotalElements())
                                  .totalPages(responsePage.getTotalPages())
                                  .isLastPage(responsePage.isLast())
                                  .build();
    }

    @Override
    public PatientResponse createPatient(CreatePatientRequest createPatientRequest) {

        if (patientRepository.existsByEmail(createPatientRequest.email()))
            throw new APIException("This email address already exists");

        Patient patient = patientMapper.toEntity(createPatientRequest);

        patient.setRegisteredDate(LocalDate.now());

        patientRepository.save(patient);

        return patientMapper.toDto(patient);
    }

    @Override
    @Transactional
    public PatientResponse updatePatient(UUID patientId, UpdatePatientRequest updatePatientRequest) {

        if (updatePatientRequest.email() != null) {
            if (patientRepository.existsByEmailAndIdNot(updatePatientRequest.email(), patientId))
                throw new APIException("This email address already exists");
        }

        Patient patient = patientRepository.findById(patientId).orElseThrow(() ->
                new ResourceNotFoundException("Patient", "patientId", patientId));

        patientMapper.updateEntity(updatePatientRequest, patient);

        return patientMapper.toDto(patient);
    }

    @Override
    public void deletePatient(UUID patientId) {

        if (!patientRepository.existsById(patientId))
            throw new ResourceNotFoundException("Patient", "patientId", patientId);

        patientRepository.deleteById(patientId);
    }
}
