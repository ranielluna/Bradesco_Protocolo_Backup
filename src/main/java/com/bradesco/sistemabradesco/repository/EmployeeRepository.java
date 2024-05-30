package com.bradesco.sistemabradesco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Department;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  Employee findByCode(String code);
  List <Employee> findByDepartment(Department department);

}