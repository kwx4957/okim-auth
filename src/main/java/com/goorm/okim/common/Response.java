package com.goorm.okim.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * {
 *     "code": 2031,
 *     "message": "입력하신 이메일 주소 또는 비밀번호가 올바르지 않습니다.",
 * }
 *
 * or
 *
 * {
 *      "code": 0,
 *      "message": "success",
 *      "data": {
 *          "hello": "world"
 *      }
 *  }
 *
 */
@Data
public class Response {
    private int code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public static ResponseEntity<?> ok(Object body) {
        Response response = new Response();
        response.message = "success";
        response.data = body;
        response.code = 0;
        return ResponseEntity.ok().body(response);
    }

    public static ResponseEntity<?> badRequest(int errorCode, String errorMessage) {
        Response response = new Response();
        response.message = errorMessage;
        response.code = errorCode;
        return ResponseEntity.badRequest().body(response);
    }

    public static ResponseEntity<?> unAuthorize(int errorCode, String errorMessage) {
        Response response = new Response();
        response.message = errorMessage;
        response.code = errorCode;
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public static ResponseEntity<Object> toFailObject(String message) {
        Response body = new Response();
        body.code = -1;
        body.message = message;
        return ResponseEntity.badRequest().body(body);
    }
}
