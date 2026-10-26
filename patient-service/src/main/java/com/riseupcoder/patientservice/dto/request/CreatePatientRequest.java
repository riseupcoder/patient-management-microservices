package com.riseupcoder.patientservice.dto.request;

import java.time.LocalDate;

public record CreatePatientRequest(String name, String email, String address,
                                   LocalDate dateOfBirth) {
}
