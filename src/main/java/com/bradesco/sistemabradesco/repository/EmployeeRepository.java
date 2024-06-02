package com.bradesco.sistemabradesco.repository;



import java.util.List;
import java.util.Optional;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.models.Employee;
import com.bradesco.sistemabradesco.models.Position;



@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
  // Optional <Employee> findByCode(managerCode);
  Optional <Employee> findByCode(String code);
  List <Employee> findByDepartment(Department department);
  List<Employee> findByPosition(Position position);
}