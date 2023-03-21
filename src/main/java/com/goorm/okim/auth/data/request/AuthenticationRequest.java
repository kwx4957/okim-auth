package com.goorm.okim.auth.data.request;

import lombok.Data;


@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
