package com.sprk.rest_api.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeDto {

    private String empId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String department;

    private String designation;
}
