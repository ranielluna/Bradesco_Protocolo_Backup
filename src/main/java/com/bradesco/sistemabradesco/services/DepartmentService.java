package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.bradesco.sistemabradesco.models.Department;
import com.bradesco.sistemabradesco.dto.DepartmentDTO;
import com.bradesco.sistemabradesco.repository.DepartmentRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    

    /* metodo criar */
     public Department addDepartment(DepartmentDTO departmentDTO){
        Department newDepartment = new Department();
        BeanUtils.copyProperties(departmentDTO, newDepartment);
        return departmentRepository.save(newDepartment);
    }

    /* metodo deletar */
    @Transactional
    public void deleteDepartment(int code){
        departmentRepository.deleteById(code);
    }

    /* listar departamento */
    public List<Department> listDepartments(){
        return departmentRepository.findAll();
    }
    
}
