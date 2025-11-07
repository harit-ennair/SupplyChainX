package org.example.supplychainx.security;

import org.example.supplychainx.model.common.User;
import org.example.supplychainx.model.common.RoleEnum;
import org.example.supplychainx.repository.common.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class SecurityAspect {

    private final UserRepository userRepository;

    @Before("@annotation(checkRole)")
    public void authorize(JoinPoint joinPoint, CheckRole checkRole) {
        var attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) throw new RuntimeException("Unauthorized: no request context");

        var request = attrs.getRequest();
        String email = request.getHeader("X-User-Email");
        String password = request.getHeader("X-User-Password");

        if (email == null || password == null)
            throw new RuntimeException("Unauthorized: missing headers");

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Unauthorized: user not found"));

        if (!user.getPassword().equals(password))
            throw new RuntimeException("Unauthorized: invalid password");

        boolean allowed = Arrays.stream(checkRole.value())
                .anyMatch(role -> role == user.getRole());

        if (!allowed)
            throw new RuntimeException("Forbidden: access denied for role " + user.getRole());
    }
}
