package com.goorm.okim.auth;

import lombok.Data;

import java.util.Objects;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    public static AuthenticationResponse create(String accessToken, String refreshToken) {
        Objects.requireNonNull(accessToken, "accessToken must not be null");
        Objects.requireNonNull(refreshToken, "refreshToken must not be null");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.accessToken = accessToken;
        authenticationResponse.refreshToken = refreshToken;
        return authenticationResponse;
    }


}
