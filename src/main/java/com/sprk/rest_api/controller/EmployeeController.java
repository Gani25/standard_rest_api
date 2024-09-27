package com.sprk.rest_api.controller;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.dto.ResponseDto;
import com.sprk.rest_api.entity.Employee;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/employee")
    public ResponseEntity<ResponseDto<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }

//    @DeleteMapping("/employee/{empId}")

}
