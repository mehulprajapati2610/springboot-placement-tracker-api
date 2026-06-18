package com.placementtracker.placement_tracker.service;

import com.placementtracker.placement_tracker.dto.request.ApplicationRequest;
import com.placementtracker.placement_tracker.dto.request.UpdateApplicationStatusRequest;
import com.placementtracker.placement_tracker.dto.response.ApplicationResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse apply(ApplicationRequest request);
    List<ApplicationResponse> getMyApplication();
    ApplicationResponse updateApplicationStatus(Long id, UpdateApplicationStatusRequest request);
    List<ApplicationResponse> getAllApplications();
}
