package com.placementtracker.placement_tracker.service.impl;

import com.placementtracker.placement_tracker.dto.request.ApplicationRequest;
import com.placementtracker.placement_tracker.dto.request.UpdateApplicationStatusRequest;
import com.placementtracker.placement_tracker.dto.response.ApplicationResponse;
import com.placementtracker.placement_tracker.entity.Application;
import com.placementtracker.placement_tracker.entity.Company;
import com.placementtracker.placement_tracker.entity.User;
import com.placementtracker.placement_tracker.exception.AlreadyAppliedException;
import com.placementtracker.placement_tracker.exception.ResourceNotFoundException;
import com.placementtracker.placement_tracker.repository.ApplicationRepository;
import com.placementtracker.placement_tracker.repository.CompanyRepository;
import com.placementtracker.placement_tracker.repository.UserRepository;
import com.placementtracker.placement_tracker.service.ApplicationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.rmi.AlreadyBoundException;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, CompanyRepository companyRepository, UserRepository userRepository) {
        this.applicationRepository = applicationRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ApplicationResponse apply(ApplicationRequest request) {
        Authentication authentication =
                SecurityContextHolder
                        .getContext ()
                        .getAuthentication ();
        String email = authentication.getName ();

        User user = userRepository.findByEmail ( email )
                .orElseThrow ( () -> new RuntimeException ("User not found") );

        Company company = companyRepository.findById ( request.getCompanyId () )
                .orElseThrow (() -> new ResourceNotFoundException ("Company not found"));

        if(applicationRepository.existsByUserAndCompany ( user, company )) {
            throw new AlreadyAppliedException ("You have already applied to this company.");
        }

        Application application = Application.builder ()
                .user ( user )
                .company ( company )
                .build ();

        Application savedApplication = applicationRepository.save ( application );

        return mapToResponse(savedApplication);
    }

    @Override
    public List<ApplicationResponse> getMyApplication() {
        Authentication authentication = SecurityContextHolder
                .getContext ()
                .getAuthentication ();

        String email = authentication.getName ();

        User user = userRepository.findByEmail ( email )
                .orElseThrow (() ->
                        new ResourceNotFoundException ( "User not found" ));

        List<Application> applications = applicationRepository.findByUser ( user );

        return applications.stream()
                .map(this::mapToResponse)
                .toList();

    }

    @Override
    public ApplicationResponse updateApplicationStatus(Long id, UpdateApplicationStatusRequest request) {
        Application application = applicationRepository.findById ( id )
                .orElseThrow (() -> new ResourceNotFoundException ( "Application not found" ));

        application.setStatus ( request.getStatus () );

        Application updatedApplication = applicationRepository.save ( application );

        return mapToResponse(updatedApplication);

    }

    @Override
    public List<ApplicationResponse> getAllApplications() {
        List<Application> applications = applicationRepository.findAll ();

        return applications.stream ()
                .map(this::mapToResponse)
                .toList ();
    }

    private ApplicationResponse mapToResponse(
            Application application) {

        return ApplicationResponse.builder()
                .id(application.getId())
                .studentName(application.getUser().getName())
                .companyName(application.getCompany().getCompanyName())
                .status(application.getStatus())
                .appliedAt(application.getAppliedAt())
                .build();
    }
}
