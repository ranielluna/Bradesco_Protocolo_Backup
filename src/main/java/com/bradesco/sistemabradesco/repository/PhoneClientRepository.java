package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.PhoneClient;

@Repository
public interface PhoneClientRepository extends JpaRepository<PhoneClient, Integer>{
   PhoneClient findByCode(int code);
  
} 
