package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.SituationProtocol;

@Repository
public interface SituationProtocolRepository extends JpaRepository<SituationProtocol, Integer>{
    SituationProtocol findByCode(int code);


}
