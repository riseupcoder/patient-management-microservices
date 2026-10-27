package com.riseupcoder.patientservice.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, String fieldName, UUID fieldId) {
        super(String.format("%s with %s: %s not found", resourceName, fieldName, fieldId));
    }

}
