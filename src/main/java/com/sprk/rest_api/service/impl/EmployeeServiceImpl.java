package com.sprk.rest_api.service.impl;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.entity.Employee;
import com.sprk.rest_api.exception.EmployeeByEmpIdNotFound;
import com.sprk.rest_api.exception.EmployeeWithEmailAlreadyExists;
import com.sprk.rest_api.exception.EmployeeWithPhoneAlreadyExists;
import com.sprk.rest_api.mapper.EmployeeMapper;
import com.sprk.rest_api.repository.EmployeeRepository;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
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

        // Check if the email or phone already exists in the system
        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent( employee -> {
                    throw new EmployeeWithEmailAlreadyExists("Employee with email " +employee.getEmail() + " already exists", 400);
                });

        employeeRepository.findByPhone(employeeDto.getPhone())
                .ifPresent(employee -> {
                    throw new EmployeeWithPhoneAlreadyExists("Employee with phone " + employee.getPhone() + " already exists", 400);
                });

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


    @Override
    public List<EmployeeDto> getAllEmployees() {
//        GPT Code
        return employeeRepository.findAll()
                .stream()
                .map(employee -> EmployeeMapper.ConvertEmployeeToDto(employee, new EmployeeDto()))
                .toList();
    }

    @Override
    public EmployeeDto getByEmpId(String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!",404));

        return EmployeeMapper.ConvertEmployeeToDto(employee, new EmployeeDto());
    }

    @Override
    public void deleteEmployee(String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!",404));
        employeeRepository.delete(employee);


    }

    @Override
    public EmployeeDto updateEmployeeByEmpId(EmployeeDto employeeDto, String empId) {
        Employee employee = employeeRepository.findByEmpId(empId).orElseThrow(()-> new EmployeeByEmpIdNotFound("Employee with " + empId + " not found!",404));

        employeeRepository.updateEmployee(employeeDto.getFirstName(),employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getPhone(),employeeDto.getDepartment(), employeeDto.getDesignation(), empId);

        employeeDto.setEmpId(empId);
        return employeeDto;
    }


    /*
    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        for(Employee employee : employees) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto = EmployeeMapper.ConvertEmployeeToDto(employee, employeeDto);

            employeeDtos.add(employeeDto);
        }
        return employeeDtos;
    }
    */




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
