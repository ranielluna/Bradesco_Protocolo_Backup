package com.bradesco.sistemabradesco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.bradesco.sistemabradesco.models.Department;
import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByCode(int code);
   
}