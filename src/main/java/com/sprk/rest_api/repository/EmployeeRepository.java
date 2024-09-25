package com.sprk.rest_api.repository;

import com.sprk.rest_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByPhone(String phone);
}
