package com.example.pcr.service;

import com.example.pcr.dto.reponse.AuthResponse;
import com.example.pcr.dto.request.AuthRequest;
import com.example.pcr.dto.request.RegisterRequest;
import com.example.pcr.entity.User;
import com.example.pcr.exception.custom.CustomAppException;
import com.example.pcr.repository.AuthRepository;
import com.example.pcr.utils.JwtUtil;
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

    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setFullName(request.fullName);
        user.setPhoneNumber(request.phoneNumber);
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(User.Role.valueOf(request.role.toUpperCase()));

        if (authRepository.existsByEmail(request.getEmail())) {
            throw new CustomAppException("Email already in use", 409);
        }

        authRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token, user.getRole().name());
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
