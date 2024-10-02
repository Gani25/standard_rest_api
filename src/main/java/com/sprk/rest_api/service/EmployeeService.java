package com.sprk.rest_api.service;


import com.sprk.rest_api.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getByEmpId(String empId);

    void deleteEmployee(String empId);
}
