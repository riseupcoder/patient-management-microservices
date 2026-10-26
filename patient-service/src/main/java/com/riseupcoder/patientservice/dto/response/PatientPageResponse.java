package com.riseupcoder.patientservice.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record PatientPageResponse(List<PatientResponse> contents, int page, int pageSize,
                                  long totalElements, int totalPages,
                                  boolean isLastPage) {
}
