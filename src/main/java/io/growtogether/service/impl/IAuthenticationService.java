package io.growtogether.service.impl;

import io.growtogether.dto.LoginRequest;
import io.growtogether.dto.AuthenticationResponse;
import io.growtogether.dto.UserRegistrationRequest;
import io.growtogether.model.ApplicationUser;

public interface IAuthenticationService {
    ApplicationUser register(UserRegistrationRequest registrationRequest);

    AuthenticationResponse login(LoginRequest loginRequest) throws Exception;
}
