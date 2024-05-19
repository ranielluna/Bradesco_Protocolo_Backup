package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bradesco.sistemabradesco.models.TipoTelefone;

public interface TipoTelefoneRepository extends JpaRepository<TipoTelefone, Integer> {
  TipoTelefone findByCodigo(int codigo);
}
