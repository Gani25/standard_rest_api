package com.sprk.rest_api.controller;

import com.sprk.rest_api.dto.EmployeeDto;
import com.sprk.rest_api.dto.ErrorResponseDto;
import com.sprk.rest_api.dto.ResponseDto;
import com.sprk.rest_api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Employee CRUD Api",
        description = "Create, Read, Update, Delete mappings of Employee"
)
@RequestMapping("/api")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            description = "Employee Post API to save Employee Object",
            summary = "Save Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Saved Successfully",
                            content= @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "statusCode": "200",
                                        "data": {
                                            "employeeId": 1,
                                            "employeeName": "John Doe",
                                            "designation": "Software Engineer"
                                        }
                                    }
                                    """)
                    )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Email or Phone Number Already Exists",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    )
            }

    )
    @PostMapping("/employee")
    public ResponseEntity<ResponseDto<EmployeeDto>> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }


    @GetMapping("/employee")
    public ResponseEntity<ResponseDto<List<EmployeeDto>>> getAllEmployees() {

        List<EmployeeDto> employeeDtoList = employeeService.getAllEmployees();
        return ResponseEntity.ok(new ResponseDto("200", employeeDtoList));
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> getEmployeeByEmpId(@PathVariable String empId) {

        EmployeeDto employeeDto = employeeService.getByEmpId(empId);

        return ResponseEntity.ok(new ResponseDto("200", employeeDto));
    }

    @DeleteMapping("/employee/{empId}")
    @Operation(
            description = "Employee Delete API to delete Employee Object",
            summary = "Delete Mapping"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Deleted Successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Employee with emp id not found",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = ErrorResponseDto.class
                                    )
                            )

                    )
            }

    )
    public ResponseEntity<ResponseDto<String>> deleteEmployeeByEmpId(@PathVariable String empId) {

        employeeService.deleteEmployee(empId);

        return ResponseEntity.status(200).body(new ResponseDto("200", ("Employee with emp id: " + empId + " deleted successfully")));
    }

    //    Using custom query

    @PutMapping("/employee/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable String empId) {

        EmployeeDto employeeDto1 = employeeService.updateEmployeeByEmpId(employeeDto, empId);


        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }


    //    Using custom query
    @PutMapping("/employee/v1/{empId}")
    public ResponseEntity<ResponseDto<EmployeeDto>> updateEmployeeV1(@RequestBody EmployeeDto employeeDto, @PathVariable String empId) {

        EmployeeDto employeeDto1 = employeeService.updateEmployeeByEmpIdV1(employeeDto, empId);


        return ResponseEntity.ok(new ResponseDto("200", employeeDto1));
    }
}
