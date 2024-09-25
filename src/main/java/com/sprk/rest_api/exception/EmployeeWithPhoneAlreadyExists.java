package com.sprk.rest_api.exception;

public class EmployeeWithPhoneAlreadyExists extends RuntimeException {

    String message;

    public EmployeeWithPhoneAlreadyExists(String message) {
        super(message);
    }
}
