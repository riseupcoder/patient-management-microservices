package com.riseupcoder.patientservice.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreatePatientRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Name cannot exceed 100 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Address is required")
        String address,

        @NotNull(message = "Date of birth is required")
        @Past
        LocalDate dateOfBirth) {
}
