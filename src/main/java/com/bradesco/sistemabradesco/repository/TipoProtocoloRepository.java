package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bradesco.sistemabradesco.models.TipoProtocolo;

public interface TipoProtocoloRepository extends JpaRepository<TipoProtocolo, Integer> {
  TipoProtocolo findByCodigo(int codigo);
}