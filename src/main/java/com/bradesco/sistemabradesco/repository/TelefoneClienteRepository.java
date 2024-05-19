package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bradesco.sistemabradesco.models.TelefoneCliente;

public interface TelefoneClienteRepository extends JpaRepository<TelefoneCliente, Integer>{
  TelefoneCliente findByCodigo(int codigo);
  
} 