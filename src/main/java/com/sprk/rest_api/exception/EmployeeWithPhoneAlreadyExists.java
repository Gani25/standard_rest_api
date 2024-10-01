package com.sprk.rest_api.exception;

public class EmployeeWithPhoneAlreadyExists extends ResourseNotFound {

    public EmployeeWithPhoneAlreadyExists(String message, int statusCode) {
        super(message, statusCode);
    }
}
