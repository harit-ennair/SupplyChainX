package org.example.supplychainx.security.auth;

import org.example.supplychainx.dto.auth.JwtResponse;
import org.example.supplychainx.dto.auth.LoginRequest;
import org.example.supplychainx.dto.auth.RegisterRequest;

public interface AuthService {
    JwtResponse login(LoginRequest loginRequest);
    JwtResponse register(RegisterRequest registerRequest);
    void logout(String token);
    boolean validateToken(String token);
}
