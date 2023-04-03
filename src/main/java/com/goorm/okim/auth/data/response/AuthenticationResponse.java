package com.goorm.okim.auth.data.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;

    @JsonInclude(Include.NON_NULL)
    private long userId;

    public static AuthenticationResponse of(String accessToken, String refreshToken) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.accessToken = accessToken;
        authenticationResponse.refreshToken = refreshToken;
        return authenticationResponse;
    }

    public static AuthenticationResponse of(String accessToken, String refreshToken, long userId) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.accessToken = accessToken;
        authenticationResponse.refreshToken = refreshToken;
        authenticationResponse.userId = userId;
        return authenticationResponse;
    }


}
