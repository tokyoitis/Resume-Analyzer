package com.resumeanalyzer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.resumeanalyzer.dto.AuthResponse;
import com.resumeanalyzer.dto.LoginRequest;
import com.resumeanalyzer.dto.RegisterRequest;
import com.resumeanalyzer.entity.Role;
import com.resumeanalyzer.entity.User;
import com.resumeanalyzer.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    @Autowired
    private final UserRepository userRepository ;

    @Autowired
    private final PasswordEncoder passwordEncoder ;
    @Autowired
    private final JwtService jwtService ;
    @Autowired
    private final AuthenticationManager authenticationManager ;

    public AuthResponse register(RegisterRequest request) {

        User user=User.builder().name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.ROLE_USER)
        .build(); // for registration logic


        User savedUser = userRepository.save(user);

        String token=jwtService.generateToken(user);

        return AuthResponse.builder().id(savedUser.getId()).name(savedUser.getName()).email(savedUser.getEmail()).role(savedUser.getRole().name()).token(token).build();
    }

    public AuthResponse login(LoginRequest request) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        
        User user=userRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("User not found"));

        String token=jwtService.generateToken(user);

        return AuthResponse.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).role(user.getRole().name()).token(token).build();

    }
}