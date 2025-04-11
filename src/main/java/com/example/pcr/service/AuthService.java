package com.example.pcr.service;

import com.example.pcr.audit.Auditable;
import com.example.pcr.dto.reponse.AuthResponse;
import com.example.pcr.dto.request.AuthRequest;
import com.example.pcr.dto.request.RegisterRequest;
import com.example.pcr.entity.User;
import com.example.pcr.exception.custom.CustomAppException;
import com.example.pcr.repository.AuthRepository;
import com.example.pcr.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired private AuthRepository authRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtUtil jwtUtil;

    @Transactional
    @Auditable(action = "CREATE", entityName = "User", entityIdExpression = "#user.id")
    public AuthResponse register(RegisterRequest request) {
        if (authRepository.existsByEmail(request.getEmail())) {
            throw new CustomAppException("Email already in use", 409);
        }
        User user = createUserFromRequest(request);
        User savedUser = authRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getRole().name());
    }

    private User createUserFromRequest(RegisterRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.valueOf(request.getRole().toUpperCase()));
        return user;
    }

    public AuthResponse login(AuthRequest request) {
        User user = authRepository.findByUsername(request.username)
                .orElseThrow(() -> new CustomAppException("User not found", 401));
        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getRole().name());
    }

}
