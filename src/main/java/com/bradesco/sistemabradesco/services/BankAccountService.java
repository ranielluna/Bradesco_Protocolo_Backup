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
    public BankAccount addBankAccount(BankAccountDTO bankAccountDTO){
        BankAccount newBankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountDTO, newBankAccount);
        return bankAccountRepository.save(newBankAccount);

    }

    /* deletar conta */
    @Transactional
    public void deleteBankAccount(int code){
        bankAccountRepository.deleteById(code);
    }

    /* Listar conta */
    public List<BankAccount> listBankAccounts(){
        return bankAccountRepository.findAll();
    }

    /* encontrar conta por codigo, numero e agencia */
    @Transactional
    public BankAccount findByCode(int code){
        return bankAccountRepository.findByCode(code)
                   .orElse(null);
    }

    @Transactional
    public BankAccount findByAccountNumber(int accountNumber){
        return bankAccountRepository.findByAccountNumber(accountNumber)
                    .orElse(null);

    }
  
    @Transactional
    public List<BankAccount> findByAgency(int agency){
        return bankAccountRepository.findByAgency(agency);
                     
    }
   
   
}
