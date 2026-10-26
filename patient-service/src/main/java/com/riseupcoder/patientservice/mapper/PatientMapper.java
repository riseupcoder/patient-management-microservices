package com.riseupcoder.patientservice.mapper;

import com.riseupcoder.patientservice.dto.request.CreatePatientRequest;
import com.riseupcoder.patientservice.dto.request.UpdatePatientRequest;
import com.riseupcoder.patientservice.dto.response.PatientResponse;
import com.riseupcoder.patientservice.model.Patient;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    Patient toEntity(CreatePatientRequest request);

    PatientResponse toDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdatePatientRequest request, @MappingTarget Patient patient);
}
