package com.placementtracker.placement_tracker.repository;

import com.placementtracker.placement_tracker.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
