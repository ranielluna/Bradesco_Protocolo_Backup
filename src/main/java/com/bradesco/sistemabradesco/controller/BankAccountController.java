package com.bradesco.sistemabradesco.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bradesco.sistemabradesco.dto.BankAccountDTO;
import com.bradesco.sistemabradesco.services.BankAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.bradesco.sistemabradesco.repository.BankAccountRepository;
import com.bradesco.sistemabradesco.models.BankAccount;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/bankAccount")
public class BankAccountController {
    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    // Listando contas
    @Operation(description = "Lista todas as contas da aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um array list com as contas e seus dados."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/listBankAccounts")
    public List<BankAccount> listBankAccounts() {
        return bankAccountService.listBankAccounts();
    }

    // encontrando conta por codigo
    @Operation(description = "Encontra uma conta por meio do código dela.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna apenas um objeto conta com seu código."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/{code}")
    public ResponseEntity<BankAccountDTO> findByCode(@PathVariable int code) {
        BankAccount bankAccount = bankAccountService.findByCode(code);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BankAccountDTO(bankAccount));
    }

    // encontrando conta por numero
    @Operation(description = "Encontra uma conta por meio do número dela.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna apenas um objeto conta com seu número."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/{accontNumber}")
    public ResponseEntity<BankAccountDTO> findByAccountNumber(@PathVariable int accountNumber) {
        BankAccount bankAccount = bankAccountService.findByAccountNumber(accountNumber);
        if (bankAccount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BankAccountDTO(bankAccount));
    }

    // encontrando conta por agencia
    @Operation(description = "Encontra uma conta por meio da agência.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto conta com sua agência."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @GetMapping("/{agency}")
    public ResponseEntity<List<BankAccount>> findByAgency(@PathVariable Integer agency) {
        List<BankAccount> bankAccounts = bankAccountRepository.findByAgency(agency);
        if (bankAccounts.isEmpty()) {
            return ResponseEntity.status(404).body(bankAccounts);
        }
        return ResponseEntity.ok(bankAccounts);
    }

    // criando conta
    @Operation(description = "Cria uma conta na aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um novo objeto conta com suas informações."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PostMapping("/addBankAccount")
    public BankAccount addBankAccount(@RequestBody BankAccountDTO bankAccountDTO) {
        return bankAccountService.addBankAccount(bankAccountDTO);
    }

    // UPDATES
    // atualizar status da conta
    @Operation(description = "Atualiza o status de uma conta.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto conta com seus campos atualizados."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/status")
    public ResponseEntity<BankAccount> updateAccountStatus(@PathVariable int code,
            @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccount updatedAccount = bankAccountService.updateAccount(code, bankAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    // Método para atualizar o número da conta
    @Operation(description = "Atualiza o número de uma conta.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna o objeto conta com seu número atualizado."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/number")
    public ResponseEntity<BankAccount> updateAccountNumber(@PathVariable int code,
            @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccount updatedAccount = bankAccountService.updateAccountNumber(code, bankAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }

    // Método para atualizar a agência da conta
    @Operation(description = "Atualiza a agência de uma conta.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Retorna um objeto conta com sua agência atualizada."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @PutMapping("/{code}/agency")
    public ResponseEntity<BankAccount> updateAccountAgency(@PathVariable int code,
            @RequestBody BankAccountDTO bankAccountDTO) {
        BankAccount updatedAccount = bankAccountService.updateAccountAgency(code, bankAccountDTO);
        return ResponseEntity.ok(updatedAccount);
    }// UPDATES

    // deletando conta
    @Operation(description = "Deleta uma conta da aplicação.")
    @ApiResponses({

            @ApiResponse(responseCode = "200", description = "Remove a conta e exite uma mensagem de sucesso."),
            @ApiResponse(responseCode = "400", description = "Bad request.")
    })
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<Object> deleteBankAccount(@PathVariable int code) {
        bankAccountService.deleteBankAccount(code);
        Map<String, String> message = new HashMap<>();
        message.put("message", "Conta deletado com sucesso!");
        return ResponseEntity.ok(message);

    }

}
