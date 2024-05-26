package com.bradesco.sistemabradesco.repository;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;

@Repository
public interface SituationProtocolRepository extends JpaRepository<SituationProtocol, Integer>{
   SituationProtocol findByProtocol(Protocol protocol);
   SituationProtocol findByProtocolResponse(Protocol protocol);


}
