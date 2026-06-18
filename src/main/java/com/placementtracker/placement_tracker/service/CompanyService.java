package com.placementtracker.placement_tracker.service;

import com.placementtracker.placement_tracker.dto.request.CompanyRequest;
import com.placementtracker.placement_tracker.dto.response.CompanyResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse createdCompany(CompanyRequest request);
    List<CompanyResponse> getAllCompanies();
    CompanyResponse getCompanyById(Long id);
    void deleteCompany(Long id);
    CompanyResponse updateCompany(Long id, CompanyRequest request);
}
