package com.bradesco.sistemabradesco.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{
    Optional<Client> findByCpf(String cpf);
    Optional<Client> findByCnpj(String cnpj);
    Optional<Client> findByName(String name);
    Optional<Client> findByCompanyName(String companyName);

}

