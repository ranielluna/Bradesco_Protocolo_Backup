package com.bradesco.sistemabradesco.repository;

import com.bradesco.sistemabradesco.models.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
    // Você pode adicionar métodos de consulta personalizados aqui, se necessário
}
