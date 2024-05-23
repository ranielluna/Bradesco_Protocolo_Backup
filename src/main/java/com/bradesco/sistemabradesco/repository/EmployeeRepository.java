package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Employee findByCode(String code);

}