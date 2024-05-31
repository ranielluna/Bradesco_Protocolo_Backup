package com.bradesco.sistemabradesco.repository;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Protocol;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Integer>{
  Protocol findByCode(int code);
  Optional<Protocol> findByProtocolNumber(Long protocolNumber);
  List<Protocol> findByProtocolStatus(String status);

}
