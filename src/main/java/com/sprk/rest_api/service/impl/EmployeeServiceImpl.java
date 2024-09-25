package com.sprk.rest_api.service.impl;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.repository.EmployeeRepository;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void saveEmployee(EmployeeDto employeeDto) {

    }
}
