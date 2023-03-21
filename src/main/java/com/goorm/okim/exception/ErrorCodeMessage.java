package com.goorm.okim.exception;

public enum ErrorCodeMessage {

    A001(999, "접근이 불가한 요청입니다.");


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