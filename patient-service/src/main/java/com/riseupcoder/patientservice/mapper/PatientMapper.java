package com.riseupcoder.patientservice.mapper;

import com.riseupcoder.patientservice.dto.request.CreatePatientRequest;
import com.riseupcoder.patientservice.dto.request.UpdatePatientRequest;
import com.riseupcoder.patientservice.dto.response.PatientResponse;
import com.riseupcoder.patientservice.model.Patient;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    @Ignored(targets = {"id", "registeredDate"})
    Patient toEntity(CreatePatientRequest request);

    PatientResponse toDto(Patient patient);

    @Ignored(targets = {"id", "registeredDate"})
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdatePatientRequest request, @MappingTarget Patient patient);
}
