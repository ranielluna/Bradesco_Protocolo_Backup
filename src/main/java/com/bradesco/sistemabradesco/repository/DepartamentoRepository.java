package com.bradesco.sistemabradesco.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.bradesco.sistemabradesco.models.Departamento;
import java.util.Optional;


public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
    Optional<Departamento> findByCodigo(int codigo);
   
}