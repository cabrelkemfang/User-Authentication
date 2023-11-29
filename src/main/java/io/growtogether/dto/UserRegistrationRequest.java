package io.growtogether.dto;

import io.growtogether.validation.annotation.EmailExist;
import io.growtogether.validation.annotation.PasswordMatches;
import io.growtogether.validation.annotation.ValidEmail;
import io.growtogether.validation.annotation.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordMatches
public class UserRegistrationRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @ValidEmail
    @EmailExist
    private String email;
    @NotBlank
    @ValidPassword
    private String password;
    private String matchingPassword;
    @NotBlank
    private String roleName;
}
