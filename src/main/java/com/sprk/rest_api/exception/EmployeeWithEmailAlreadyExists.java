package com.sprk.rest_api.exception;

public class EmployeeWithEmailAlreadyExists extends RuntimeException {

    String message;

    public EmployeeWithEmailAlreadyExists(String message) {
        super(message);
    }
}
