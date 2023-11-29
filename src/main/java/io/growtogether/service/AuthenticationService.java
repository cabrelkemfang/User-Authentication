package io.growtogether.service;

import io.growtogether.dto.LoginRequest;
import io.growtogether.dto.AuthenticationResponse;
import io.growtogether.dto.UserRegistrationRequest;
import io.growtogether.exeption.AuthenticationBackendException;
import io.growtogether.mapper.UserMapper;
import io.growtogether.model.ApplicationUser;
import io.growtogether.repository.RoleRepository;
import io.growtogether.repository.UserRepository;
import io.growtogether.service.impl.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    public ApplicationUser register(UserRegistrationRequest registrationRequest) {
        var role = this.roleRepository.findByAuthority(registrationRequest.getRoleName())
                .orElseThrow(() -> new AuthenticationBackendException("Role {} not found" + registrationRequest.getRoleName(), HttpStatus.NOT_FOUND.value()));

        return userRepository.save(this.userMapper.mapToApplicationUser(registrationRequest, role));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) throws Exception {
        try {
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            return this.userMapper.mapToAuthenticationResponse(tokenService.generateToken(auth), loginRequest.getEmail());
        } catch (AuthenticationException e) {
            log.info(e.getMessage());
            throw new AuthenticationBackendException(e.getMessage(), HttpStatus.UNAUTHORIZED.value());
        }
    }

}
