package com.placementtracker.placement_tracker.service.impl;

import com.placementtracker.placement_tracker.dto.request.CompanyRequest;
import com.placementtracker.placement_tracker.dto.response.CompanyResponse;
import com.placementtracker.placement_tracker.entity.Company;
import com.placementtracker.placement_tracker.exception.ResourceNotFoundException;
import com.placementtracker.placement_tracker.repository.CompanyRepository;
import com.placementtracker.placement_tracker.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse createdCompany(CompanyRequest request) {
        Company company = Company.builder ()
                .companyName ( request.getCompanyName () )
                .role ( request.getRole () )
                .packageLpa ( request.getPackageLpa () )
                .location ( request.getLocation () )
                .applicationDeadline ( request.getApplicationDeadline () )
                .description ( request.getDescription ())
                .build ();

        Company savedCompany = companyRepository.save ( company );

        return mapToResponse(savedCompany);
    }

    @Override
    public List<CompanyResponse> getAllCompanies() {
        List<Company> companies = companyRepository.findAll ();
        return companies.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CompanyResponse getCompanyById(Long id) {
        Company company = companyRepository.findById (id)
                .orElseThrow ( () -> new RuntimeException ("Company not found."));

        return mapToResponse(company);
    }

    @Override
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById ( id ).orElseThrow (() -> new RuntimeException ("Company not found"));

        companyRepository.delete ( company );
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {
        Company company = companyRepository.findById ( id ).orElseThrow (() -> new ResourceNotFoundException ( "Company not found" ) );

        company.setCompanyName ( request.getCompanyName () );
        company.setRole ( request.getRole () );
        company.setPackageLpa ( request.getPackageLpa () );
        company.setLocation ( request.getLocation () );
        company.setApplicationDeadline (request.getApplicationDeadline ());
        company.setDescription ( request.getDescription () );

        Company updatedCompany = companyRepository.save ( company );

        return mapToResponse ( updatedCompany );

    }

    private CompanyResponse mapToResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .role(company.getRole())
                .packageLpa(company.getPackageLpa())
                .location(company.getLocation())
                .applicationDeadline(company.getApplicationDeadline())
                .description(company.getDescription())
                .createdAt(company.getCreatedAt())
                .build();
    }



}
