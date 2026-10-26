package com.riseupcoder.patientservice.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponse(UUID id, String name, String email, String address,
                              LocalDate dateOfBirth, LocalDate registeredDate) {
}
