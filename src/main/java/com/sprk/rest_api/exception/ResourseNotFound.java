package com.sprk.rest_api.exception;

import lombok.Getter;

@Getter
public class ResourseNotFound extends RuntimeException{

    int statusCode;
    public ResourseNotFound(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
