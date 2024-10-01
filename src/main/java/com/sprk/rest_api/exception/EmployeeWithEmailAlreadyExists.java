package com.sprk.rest_api.exception;

public class EmployeeWithEmailAlreadyExists extends ResourseNotFound {

    public EmployeeWithEmailAlreadyExists(String message, int statusCode) {
        super(message,statusCode);
    }
}
