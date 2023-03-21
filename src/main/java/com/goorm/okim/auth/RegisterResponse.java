package com.goorm.okim.auth;

import com.goorm.okim.user.User;
import lombok.Getter;

import java.util.Objects;

@Getter
public class RegisterResponse {
    private String accessToken;
    private String refreshToken;

    public static RegisterResponse create(String accessToken, String refreshToken) {
        Objects.requireNonNull(accessToken, "accessToken must not be null");
        Objects.requireNonNull(refreshToken, "refreshToken must not be null");

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.accessToken = accessToken;
        registerResponse.refreshToken = refreshToken;
        return registerResponse;
    }
}
