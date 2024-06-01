package com.bradesco.sistemabradesco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bradesco.sistemabradesco.models.BankAccount;
import java.util.Optional;
import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{
    Optional<BankAccount> findByCode(int code);
    Optional<BankAccount>findByAccountNumber(int accountNumber);
    List<BankAccount> findByAgency(int agency);
    /* encontrar contas por outras variaveis */
}
