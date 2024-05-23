package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer>{
  Protocol findByCodigo(int codigo);
  Protocol findByNumeroProtocolo(Long numeroProtocolo);
}
