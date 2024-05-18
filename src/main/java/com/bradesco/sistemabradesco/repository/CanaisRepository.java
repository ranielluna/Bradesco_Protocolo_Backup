package com.bradesco.sistemabradesco.repository;

import com.bradesco.sistemabradesco.models.Canais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanaisRepository extends JpaRepository<Canais, Integer> {
}
