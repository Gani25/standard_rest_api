package com.sprk.rest_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Locked;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String empId; // emp_id

    private String firstName; // first_name

    private String lastName;

    private String email;

    @Column(length = 15)
    private String phone;

    private String department;

    private String designation;

    private LocalDate dateOfJoining;



}
