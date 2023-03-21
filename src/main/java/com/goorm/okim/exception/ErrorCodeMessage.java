package com.goorm.okim.exception;

public enum ErrorCodeMessage {

    AUTH_NOT_FOUND(1000, "로그인 정보가 잘못되었습니다."),
    AUTH_DISABLED(1001, "로그인 정보가 잘못되었습니다."),
    AUTH_WRONG_PASSWORD(1002, "잘못된 패스워드 입니다.");

    private final int code;
    private final String message;

    ErrorCodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}