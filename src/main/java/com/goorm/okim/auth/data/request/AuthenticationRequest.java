package com.goorm.okim.auth.data.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AuthenticationRequest {
    @Email(message = "{email.invalid.format}")
    @NotNull(message = "{email.required}")
    private String email;

    @NotNull(message = "{password.required}")
    @NotBlank(message = "{password.required}")
    @Size(min = 8, max = 20, message = "{password.length}")
    private String password;
}