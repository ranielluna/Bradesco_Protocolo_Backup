package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bradesco.sistemabradesco.models.SituationProtocol;



public interface SituationProtocolRepository extends JpaRepository<SituationProtocol, Integer>{
    SituationProtocol  findByCodigo(int codigo);


}
