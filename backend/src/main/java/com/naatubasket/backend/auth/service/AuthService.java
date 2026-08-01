package com.naatubasket.backend.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.naatubasket.backend.auth.dto.LoginRequest;
import com.naatubasket.backend.auth.dto.LoginResponse;
import com.naatubasket.backend.auth.dto.RegisterRequest;
import com.naatubasket.backend.auth.dto.RegisterResponse;
import com.naatubasket.backend.auth.entity.Role;
import com.naatubasket.backend.auth.entity.User;
import com.naatubasket.backend.auth.entity.UserRole;
import com.naatubasket.backend.auth.repository.RoleRepository;
import com.naatubasket.backend.auth.repository.UserRepository;
import com.naatubasket.backend.auth.repository.UserRoleRepository;
import com.naatubasket.backend.common.exception.DuplicateResourceException;
import com.naatubasket.backend.common.exception.ResourceNotFoundException;
import com.naatubasket.backend.security.jwt.JwtProperties;
import com.naatubasket.backend.security.jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final JwtProperties jwtProperties;

    /**
     * Register a new customer.
     */
    public RegisterResponse register(RegisterRequest request) {

        // Check phone number
        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new DuplicateResourceException(
                    "Phone number already registered.");
        }

        // Check email
        if (request.getEmail() != null
                && !request.getEmail().isBlank()
                && userRepository.existsByEmail(request.getEmail())) {

            throw new DuplicateResourceException(
                    "Email already registered.");
        }

        // Create user
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .phoneVerified(false)
                .emailVerified(false)
                .accountStatus("ACTIVE")
                .build();

        user = userRepository.save(user);

        // Assign CUSTOMER role
        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "CUSTOMER role not found."));

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(customerRole)
                .build();

        userRoleRepository.save(userRole);

        return RegisterResponse.builder()
                .userId(user.getId())
                .message("User registered successfully.")
                .build();
    }

    /**
     * Login using phone number and password.
     */
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByPhoneNumber(request.getPhoneNumber())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invalid phone number or password."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            throw new ResourceNotFoundException(
                    "Invalid phone number or password.");
        }

        String accessToken =
                jwtService.generateAccessToken(user.getPhoneNumber());

        String refreshToken =
                jwtService.generateRefreshToken(user.getPhoneNumber());

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(jwtProperties.getAccessTokenExpiration())
                .build();
    }

}