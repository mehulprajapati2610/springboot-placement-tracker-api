package com.placementtracker.placement_tracker.dto.response;

import com.placementtracker.placement_tracker.entity.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {
    private Long id;
    private String studentName;
    private String companyName;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;
}
