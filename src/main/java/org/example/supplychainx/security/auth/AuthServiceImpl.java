package org.example.supplychainx.security.auth;

import org.example.supplychainx.dto.auth.JwtResponse;
import org.example.supplychainx.dto.auth.LoginRequest;
import org.example.supplychainx.dto.auth.RegisterRequest;
import org.example.supplychainx.exception.BusinessException;
import org.example.supplychainx.model.common.RoleEnum;
import org.example.supplychainx.model.common.User;
import org.example.supplychainx.repository.common.UserRepository;
import org.example.supplychainx.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtService jwtService,
                           UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ===============================================================================================================
    @Override
    public  JwtResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new BusinessException("Utilisateur non trouvé."));

            return createJwtResponse(user);

        } catch (BadCredentialsException ex) {
            throw new BusinessException("Nom d'utilisateur ou mot de passe invalide.");
        } catch (AuthenticationException ex) {
            throw new BusinessException("Erreur d'authentification.");
        }
    }


    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new BusinessException("Un utilisateur avec ce nom d'utilisateur existe déjà.");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new BusinessException("Un utilisateur avec cet email existe déjà.");
        }

        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole(parseRole(registerRequest.getRole()));

        User savedUser = userRepository.save(newUser);
        return createJwtResponse(savedUser);
    }
    // ===============================================================================================================

    @Override
    public void logout(String token) {
        // TODO: Implement token invalidation logic
    }

    @Override
    public boolean validateToken(String token) {
        return jwtService.validateToken(token);
    }

    private JwtResponse createJwtResponse(User user) {
        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());
        return new JwtResponse(token, user.getUsername(), user.getEmail(), user.getRole().name());
    }

    private RoleEnum parseRole(String role) {
        try {
            return RoleEnum.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return RoleEnum.USER;
        }
    }
}
