package com.goorm.okim.common;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class Response {
    private int code;
    private String message;
    private Object data;

    public static ResponseEntity<?> ok(Object body) {
        Response response = new Response();
        response.message = "success";
        response.data = body;
        response.code = 0;
        return ResponseEntity.ok().body(response);
    }

    public static ResponseEntity<?> fail(int errorCode, String errorMessage) {
        Response response = new Response();
        response.message = errorMessage;
        response.code = errorCode;
        return ResponseEntity.badRequest().body(response);
    }
}
