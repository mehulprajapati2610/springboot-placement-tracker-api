package com.placementtracker.placement_tracker.controller;

import com.placementtracker.placement_tracker.dto.request.CompanyRequest;
import com.placementtracker.placement_tracker.dto.response.CompanyResponse;
import com.placementtracker.placement_tracker.entity.Company;
import com.placementtracker.placement_tracker.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@Valid @RequestBody CompanyRequest request) {
        CompanyResponse response = companyService.createdCompany ( request );

        return ResponseEntity.status ( HttpStatus.CREATED ).body ( response );
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponse>> getAllCompanies(){
        return ResponseEntity.ok (companyService.getAllCompanies ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok (companyService.getCompanyById ( id ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        companyService.deleteCompany ( id );
        return ResponseEntity.ok ("Company deleted successully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequest request) {
        return ResponseEntity.ok (companyService.updateCompany ( id, request ));
    }
}
