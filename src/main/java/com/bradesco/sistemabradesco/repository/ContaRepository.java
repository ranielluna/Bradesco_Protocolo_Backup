package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bradesco.sistemabradesco.models.Conta;
import java.util.Optional;
import java.util.List;




public interface ContaRepository extends JpaRepository<Conta, Integer>{
    Optional<Conta> findByCodigo(int codigo);
    Optional<Conta>findByNumeroConta(int numeroConta);
    List<Conta> findByAgencia(int agencia);
    /* encontrar contas por outras variaveis */
}
