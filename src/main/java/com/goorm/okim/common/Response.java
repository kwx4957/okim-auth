package com.goorm.okim.common;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String message;
    private Object data;

    public static Response ok(Object body) {
        Response response = new Response();
        response.message = "success";
        response.data = body;
        response.code = 0;
        return response;
    }

}
