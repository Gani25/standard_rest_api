package com.sprk.rest_api.service.impl;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.entity.Employee;
import com.sprk.rest_api.exception.EmployeeWithEmailAlreadyExists;
import com.sprk.rest_api.exception.EmployeeWithPhoneAlreadyExists;
import com.sprk.rest_api.mapper.EmployeeMapper;
import com.sprk.rest_api.repository.EmployeeRepository;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        employeeDto.setEmpId(null);

        Optional<Employee> employeeWithEmail = employeeRepository.findByEmail(employeeDto.getEmail());
        Optional<Employee> employeeWithPhone = employeeRepository.findByPhone(employeeDto.getPhone());

        if (employeeWithEmail.isPresent()) {
            throw new EmployeeWithEmailAlreadyExists("Employee with " + employeeDto.getEmail() + " already exists");
        }
        if (employeeWithPhone.isPresent()) {
            throw new EmployeeWithPhoneAlreadyExists("Employee with " + employeeDto.getPhone() + " already exists");
        }

        // Convert employeeDto to Employee;
        Employee employee = new Employee();

        employee = EmployeeMapper.ConvertEmployeeDtoToEmployee(employeeDto, employee);

        // Logic to generate empID
        employee.setEmpId(generateEmpId(employee.getFirstName()));
        employee.setDateOfJoining(LocalDate.now());

//        Employee savedEmployee=
        employeeRepository.save(employee);

        employeeDto.setEmpId(employee.getEmpId());
        return employeeDto;

    }

    private String generateEmpId(String firstName) {
        // Get the first two characters of the first name
        String company = "SPRK";
        String fNameChar = firstName.substring(0, 2).toUpperCase();

        // Get the current year
        String year = String.valueOf(Year.now().getValue()).substring(2);

        // Generate a random UUID (universally unique identifier)
        String uniqueId = UUID.randomUUID().toString().substring(0, 8); // Get first 8 chars of UUID

        // Combine prefix, year, and UUID to form the unique empId
        return (company + year + fNameChar + uniqueId).toUpperCase();
    }
}
