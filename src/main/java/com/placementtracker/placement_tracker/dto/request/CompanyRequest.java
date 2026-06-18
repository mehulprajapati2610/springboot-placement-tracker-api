package com.placementtracker.placement_tracker.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequest {

    @NotBlank
    private String companyName;

    @NotBlank
    private String role;

    @Positive
    private Double packageLpa;

    @NotBlank
    private String location;

    @NotNull
    private LocalDate applicationDeadline;

    @NotBlank
    private String description;

}
