package com.sprk.rest_api.exception;

import com.sprk.rest_api.dto.ErrorResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandlerAdvice {

    @ExceptionHandler(EmployeeWithEmailAlreadyExists.class)
    public ResponseEntity<?> handleEmployeeWithEmailAlreadyExists(EmployeeWithEmailAlreadyExists employeeWithEmailAlreadyExists, HttpServletRequest request) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(request.getContextPath(), "500",employeeWithEmailAlreadyExists.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(500).body(errorResponseDto);
    }


}
