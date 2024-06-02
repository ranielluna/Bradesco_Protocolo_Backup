package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.BankAccountDTO;
import com.bradesco.sistemabradesco.models.BankAccount;
import com.bradesco.sistemabradesco.repository.BankAccountRepository;
import java.util.List;

import jakarta.transaction.Transactional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    /* Criar conta */
    public BankAccount addBankAccount(BankAccountDTO bankAccountDTO) {
        BankAccount newBankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, newBankAccount);
        return bankAccountRepository.save(newBankAccount);

    }

    /* deletar conta */
    @Transactional
    public void deleteBankAccount(int code) {
        bankAccountRepository.deleteById(code);
    }

    /* Listar conta */
    public List<BankAccount> listBankAccounts() {
        return bankAccountRepository.findAll();
    }

    /* encontrar conta por codigo, numero e agencia */
    @Transactional
    public BankAccount findByCode(int code) {
        return bankAccountRepository.findByCode(code)
                .orElse(null);
    }

    @Transactional
    public BankAccount findByAccountNumber(int accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .orElse(null);

    }

    @Transactional
    public List<BankAccount> findByAgency(int agency) {
        return bankAccountRepository.findByAgency(agency);

    }

    // atualizar conta
    @Transactional
    public BankAccount updateAccount(int code, BankAccountDTO bankAccountDTO) {
        // Encontrar a conta existente no banco de dados
        BankAccount account = bankAccountRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada para o ID: " + code));

        // Atualizar os campos da conta com os novos valores do DTO
        account.setAccountStatus(bankAccountDTO.getAccountStatus());
        account.setAccountNumber(bankAccountDTO.getAccountNumber());
        account.setAgency(bankAccountDTO.getAgency());

        // Salvando a conta atualizada no banco de dados
        return bankAccountRepository.save(account);
    }

    // update numero
    @Transactional
    public BankAccount updateAccountNumber(int code, BankAccountDTO bankAccountDTO) {
        // Encontrar a conta existente no banco de dados
        BankAccount accountNumber = bankAccountRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada para o ID: " + code));

        accountNumber.setAccountNumber(bankAccountDTO.getAccountNumber());
        return bankAccountRepository.save(accountNumber);
    }

    // update agencia
    @Transactional
    public BankAccount updateAccountAgency(int code, BankAccountDTO bankAccountDTO) {
        // Encontrar a conta existente no banco de dados
        BankAccount agency = bankAccountRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada para o ID: " + code));

        agency.setAgency(bankAccountDTO.getAgency());
        return bankAccountRepository.save(agency);
    }// updates

}// class
