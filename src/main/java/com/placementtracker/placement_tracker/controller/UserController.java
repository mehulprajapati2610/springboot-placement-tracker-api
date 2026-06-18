package com.placementtracker.placement_tracker.controller;

import com.placementtracker.placement_tracker.dto.request.LoginRequest;
import com.placementtracker.placement_tracker.dto.request.RegisterRequest;
import com.placementtracker.placement_tracker.dto.response.AuthenticationResponse;
import com.placementtracker.placement_tracker.dto.response.LoginResponse;
import com.placementtracker.placement_tracker.dto.response.RegisterResponse;
import com.placementtracker.placement_tracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request) {

        RegisterResponse response = userService.registerUser ( request );

        return ResponseEntity.status ( HttpStatus.CREATED ).body ( response );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        AuthenticationResponse response = userService.loginUser ( request );

        return ResponseEntity.ok (response);
    }
}
