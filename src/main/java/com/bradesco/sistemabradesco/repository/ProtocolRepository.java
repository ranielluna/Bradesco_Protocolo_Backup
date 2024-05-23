package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer>{
  Protocol findByCode(int code);
  Protocol findByProtocolNumber(Long protocolNumber);
}
