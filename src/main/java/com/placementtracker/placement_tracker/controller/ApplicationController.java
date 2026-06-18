package com.placementtracker.placement_tracker.controller;

import com.placementtracker.placement_tracker.dto.request.ApplicationRequest;
import com.placementtracker.placement_tracker.dto.request.UpdateApplicationStatusRequest;
import com.placementtracker.placement_tracker.dto.response.ApplicationResponse;
import com.placementtracker.placement_tracker.entity.Application;
import com.placementtracker.placement_tracker.service.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/apply")
    public ResponseEntity<ApplicationResponse> apply(@Valid @RequestBody ApplicationRequest request) {
        ApplicationResponse response = applicationService.apply ( request );

        return ResponseEntity.status ( HttpStatus.CREATED ).body ( response );
    }

    @GetMapping("/my")
    public ResponseEntity<List<ApplicationResponse>> getMyApplication() {
        return ResponseEntity.ok (
                applicationService.getMyApplication ()
        );
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateApplicationStatusRequest request) {
        return ResponseEntity.ok (applicationService.updateApplicationStatus ( id, request ));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponse>> getAllApplications() {
        return ResponseEntity.ok (applicationService.getAllApplications ());
    }

}
