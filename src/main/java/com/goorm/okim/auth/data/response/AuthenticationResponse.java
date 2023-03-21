package com.goorm.okim.auth.data.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    public static AuthenticationResponse of(String accessToken, String refreshToken) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.accessToken = accessToken;
        authenticationResponse.refreshToken = refreshToken;
        return authenticationResponse;
    }


}
