package com.financetracker.service;

import com.financetracker.dto.AuthRequest;
import com.financetracker.dto.AuthResponse;
import com.financetracker.dto.RegisterRequest;
import com.financetracker.model.Role;
import com.financetracker.model.User;
import com.financetracker.repository.UserRepository;
import com.financetracker.service.impl.UserDetailsServiceImpl;
import com.financetracker.util.JwtUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserDetailsServiceImpl userDetailsService;

    public AuthResponse register(RegisterRequest req) {
        User user = new User();
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.USER);
        userRepo.save(user);
        return new AuthResponse(jwtUtil.generateToken(new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER"))
        )));
    }

    public AuthResponse login(AuthRequest req) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());
        if (!passwordEncoder.matches(req.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return new AuthResponse(jwtUtil.generateToken(userDetails));
    }
}

