package com.example.demo.service.impl;

import com.example.demo.dto.AuthRegisterRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User register(AuthRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // You should encode in real project
        userRepository.save(user);
        return user;
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        // You can validate password here
        return jwtTokenProvider.generateToken(user.getId(), user.getUsername(), "ROLE_USER");
    }
}
