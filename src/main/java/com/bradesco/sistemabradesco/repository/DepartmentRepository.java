package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bradesco.sistemabradesco.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByCode(int code);
   
}