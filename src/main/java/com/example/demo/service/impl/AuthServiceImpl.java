package com.example.demo.service.impl;

import com.example.demo.dto.AuthLoginRequest;
import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public AuthResponse register(AuthRegisterRequest request) {
        // Create new User
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // In real project, encode the password
        userRepository.save(user);

        // Generate token
        String token = jwtTokenProvider.generateToken(user.getId(), user.getUsername(), "ROLE_USER");

        // Return AuthResponse
        return new AuthResponse(user.getUsername(), token);
    }

    @Override
    public AuthResponse login(AuthLoginRequest request) {
        // Find user by username
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());
        if (!optionalUser.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        // Validate password (simple comparison here; in real app use encoder)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate token
        String token = jwtTokenProvider.generateToken(user.getId(), user.getUsername(), "ROLE_USER");

        // Return AuthResponse
        return new AuthResponse(user.getUsername(), token);
    }
}
