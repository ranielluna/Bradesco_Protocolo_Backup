package com.bradesco.sistemabradesco.repository;



import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Cliente;




@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByCnpj(String cnpj);
    Optional<Cliente> findByNome(String nome);
    Optional<Cliente> findByRazaoSocial(String razaoSocial);

}

