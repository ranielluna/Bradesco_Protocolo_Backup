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

    // metodo criar
    @Transactional
    public Department addDepartment(DepartmentDTO departmentDTO) {
        Department newDepartment = new Department();
        BeanUtils.copyProperties(departmentDTO, newDepartment);
        return departmentRepository.save(newDepartment);
    }

    // metodo de atualizar departamento
    @Transactional
    public Department updateDepartment(int code, DepartmentDTO departmentDTO) {
        // encontrando o departamento pelo id
        Department department = departmentRepository.findByCode(code);
        // .orElseThrow(()-> new RuntimeException("Departamento não encontrado por este
        // código:" + code));
        department.setDepartment(departmentDTO.getDepartment());
        return departmentRepository.save(department);
    }

    // metodo deletar
    @Transactional
    public void deleteDepartment(int code) {
        departmentRepository.deleteById(code);
    }

    // listar departamento
    public List<Department> listDepartments() {
        return departmentRepository.findAll();
    }

}
