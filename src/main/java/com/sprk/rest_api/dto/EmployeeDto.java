package com.sprk.rest_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Schema(
        name = "Employee",
        description = "Used to hold Employee Information"
)
public class EmployeeDto {

    @Schema(
            description = "employee id",
            example = "Auto Generated"
    )
    private String empId;

    @Schema(
            description = "first name",
            example = "John"
    )
    private String firstName;
    @Schema(
            description = "last name",
            example = "Doe"
    )
    private String lastName;

    @Schema(
            description = "Email - must be unique",
            example = "john@gmail.com"
    )
    private String email;

    @Schema(
            description = "Phone - must be unique",
            example = "4584526595"
    )
    private String phone;

    @Schema(
            description = "Department",
            example = "Sales"
    )
    private String department;

    @Schema(
            description = "Designation",
            example = "Manager"
    )
    private String designation;
}
