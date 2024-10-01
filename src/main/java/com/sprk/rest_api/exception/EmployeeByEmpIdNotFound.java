package com.sprk.rest_api.exception;

public class EmployeeByEmpIdNotFound extends ResourseNotFound {


    public EmployeeByEmpIdNotFound(String message, int statusCode) {
        super(message, statusCode);
    }
}
