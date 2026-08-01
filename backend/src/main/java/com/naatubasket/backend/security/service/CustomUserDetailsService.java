package com.naatubasket.backend.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.naatubasket.backend.auth.entity.User;
import com.naatubasket.backend.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository
                .findByPhoneNumber(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with phone number: " + username));

        return new CustomUserDetails(user);
    }
}