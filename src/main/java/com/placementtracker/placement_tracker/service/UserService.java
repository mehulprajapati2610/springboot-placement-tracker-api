package com.placementtracker.placement_tracker.service;

import com.placementtracker.placement_tracker.dto.request.LoginRequest;
import com.placementtracker.placement_tracker.dto.request.RegisterRequest;
import com.placementtracker.placement_tracker.dto.response.AuthenticationResponse;
import com.placementtracker.placement_tracker.dto.response.RegisterResponse;

public interface UserService {
    RegisterResponse registerUser(RegisterRequest request);
    AuthenticationResponse loginUser(LoginRequest request);
}
