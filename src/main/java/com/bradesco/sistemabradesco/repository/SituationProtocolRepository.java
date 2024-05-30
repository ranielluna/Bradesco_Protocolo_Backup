package com.bradesco.sistemabradesco.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Protocol;
import com.bradesco.sistemabradesco.models.SituationProtocol;
// import java.util.List;


@Repository
public interface SituationProtocolRepository extends JpaRepository<SituationProtocol, Integer>{
   SituationProtocol findByProtocol(Protocol protocol);
   SituationProtocol findByProtocolResponse(Protocol protocol);


}