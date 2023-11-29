package io.growtogether.mapper;

import io.growtogether.dto.AuthenticationResponse;
import io.growtogether.dto.UserRegistrationRequest;
import io.growtogether.model.ApplicationUser;
import io.growtogether.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public ApplicationUser mapToApplicationUser(UserRegistrationRequest registrationRequest, Role role) {
        return ApplicationUser.builder()
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .authorities(Collections.singleton(role))
                .email(registrationRequest.getEmail())
                .createdDate(LocalDateTime.now())
                .build();
    }

    public AuthenticationResponse mapToAuthenticationResponse(String token, String email) {
        return AuthenticationResponse.builder()
                .token(token)
                .username(email)
                .build();
    }

}
