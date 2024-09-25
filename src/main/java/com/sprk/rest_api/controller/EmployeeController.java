package com.sprk.rest_api.controller;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.entity.Employee;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/employee")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        System.out.println(employeeDto);
        return null;
    }





}
