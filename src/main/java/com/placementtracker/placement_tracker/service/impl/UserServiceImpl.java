package com.placementtracker.placement_tracker.service.impl;

import com.placementtracker.placement_tracker.dto.request.LoginRequest;
import com.placementtracker.placement_tracker.dto.request.RegisterRequest;
import com.placementtracker.placement_tracker.dto.response.AuthenticationResponse;
import com.placementtracker.placement_tracker.dto.response.LoginResponse;
import com.placementtracker.placement_tracker.dto.response.RegisterResponse;
import com.placementtracker.placement_tracker.entity.User;
import com.placementtracker.placement_tracker.entity.enums.Role;
import com.placementtracker.placement_tracker.exception.UserAlreadyExistsException;
import com.placementtracker.placement_tracker.repository.UserRepository;
import com.placementtracker.placement_tracker.service.JwtService;
import com.placementtracker.placement_tracker.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        Optional<User> byEmail = userRepository.findByEmail ( request.getEmail () );
        if (byEmail.isPresent ()) {
            throw new UserAlreadyExistsException ( "Email already exists" );
        }

        User user = new User ();
        user.setName ( request.getName () );
        user.setEmail ( request.getEmail () );
        user.setPassword ( passwordEncoder.encode ( request.getPassword () ) );
        user.setRole ( Role.USER );
        user.setCreatedAt ( LocalDateTime.now () );

        User savedUser = userRepository.save ( user );

        RegisterResponse response = new RegisterResponse();

        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());

        return response;
    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail ( request.getEmail () );

        if(userOptional.isEmpty ()) {
            throw new RuntimeException ("Invalid Email or password.");
        }

        User user = userOptional.get ();

        if(!passwordEncoder.matches ( request.getPassword (), user.getPassword () )) {
            throw new RuntimeException ( "Invalid Email or password." );
        }

        String token = jwtService.generateToken (user.getEmail ());

        return new AuthenticationResponse (token);
    }
}
