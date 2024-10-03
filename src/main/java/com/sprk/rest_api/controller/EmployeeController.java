package com.sprk.rest_api.controller;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.dto.ResponseDto;
import com.sprk.rest_api.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("/employee")
    public ResponseEntity<ResponseDto<List<EmployeeDto>>> getAllEmployees(){

        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ResponseDto("200", employeeDtoList));
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> getEmployeeByEmpId(@PathVariable String empId) {

        EmployeeDto employeeDto = employeeService.getByEmpId(empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto));
    }

    @DeleteMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<String>> deleteEmployeeByEmpId(@PathVariable String empId) {

        employeeService.deleteEmployee(empId);

        return ResponseEntity.status(200).body(new ResponseDto("200", ("Employee with emp id: "+empId+" deleted successfully")));
    }

//    Using custom query
    @PutMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable String empId) {

        EmployeeDto employeeDto1= employeeService.updateEmployeeByEmpId(employeeDto,empId);


        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }


    //    Using custom query
    @PutMapping("/employee/v1/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployeeV1(@RequestBody EmployeeDto employeeDto, @PathVariable String empId) {

        EmployeeDto employeeDto1= employeeService.updateEmployeeByEmpIdV1(employeeDto,empId);


        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }
}
