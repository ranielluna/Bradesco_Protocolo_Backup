package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.bradesco.sistemabradesco.models.Departamento;
import com.bradesco.sistemabradesco.dto.DepartarmentoDTO;
import com.bradesco.sistemabradesco.repository.DepartamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;
    
    // public DepartamentoService (DepartamentoRepository departamentoRepository){
    //     this.departamentoRepository = departamentoRepository;
    // }

    /* metodo criar */
     public Departamento criarDepartamento(DepartarmentoDTO departamentoDTO){
        Departamento novoDepartamento = new Departamento();
        BeanUtils.copyProperties(departamentoDTO, novoDepartamento);
        return departamentoRepository.save(novoDepartamento);
    }

    /* metodo deletar */
    @Transactional
    public void deletarDepartamento(int codigo){
        departamentoRepository.deleteById(codigo);
    }

    /* listar departamento */
    public List<Departamento> listarDepartamentos(){
        return departamentoRepository.findAll();
    }
    
}
