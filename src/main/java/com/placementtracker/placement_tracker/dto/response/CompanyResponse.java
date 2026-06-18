package com.placementtracker.placement_tracker.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponse {
    private Long id;
    private String companyName;
    private String role;
    private Double packageLpa;
    private String location;
    private LocalDate applicationDeadline;
    private String description;
    private LocalDateTime createdAt;
}
