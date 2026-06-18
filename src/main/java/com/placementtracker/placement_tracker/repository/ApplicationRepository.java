package com.placementtracker.placement_tracker.repository;

import com.placementtracker.placement_tracker.entity.Application;
import com.placementtracker.placement_tracker.entity.Company;
import com.placementtracker.placement_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    boolean existsByUserAndCompany(User user, Company company);
    List<Application> findByUser(User user);
}
