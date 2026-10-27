package com.riseupcoder.patientservice.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UpdatePatientRequest(

        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @Email(message = "Email should be valid")
        String email,

        String address,

        @Past
        LocalDate dateOfBirth) {
}
