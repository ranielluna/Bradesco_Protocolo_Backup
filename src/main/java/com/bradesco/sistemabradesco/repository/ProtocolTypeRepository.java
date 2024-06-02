package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.ProtocolType;

@Repository
public interface ProtocolTypeRepository extends JpaRepository<ProtocolType, Integer> {
  ProtocolType findByCode(int code);
}