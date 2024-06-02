package com.bradesco.sistemabradesco.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.ClientDTO;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.repository.ClientRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // metodo criar um novo cliente
    public Client addClient(Client client) {
        Client newCliente = new Client();
        newCliente.setCpf(client.getCpf());
        newCliente.setName(client.getName());
        newCliente.setEmail(client.getEmail());
        newCliente.setClientType(client.getClientType());
        if (client.getProfession() != null) {
            newCliente.setProfession(client.getProfession());
        }
        if (client.getCnpj() != null) {
            newCliente.setCnpj(client.getCnpj());
            newCliente.setCompanyName(client.getCompanyName());
        }

        return clientRepository.save(newCliente);
    }

    // Listar todos os clientes do Banco de dados
    public List<Client> listClients() {
        return clientRepository.findAll();
    }

    // metodos para encontrar cliente por cpf, cnpj, nome e razão social
    @Transactional
    public Client findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf)
                .orElse(null);
    }

    @Transactional
    public Client findByCnpj(String cnpj) {
        return clientRepository.findByCnpj(cnpj)
                .orElse(null);
    }

    @Transactional
    public Client findByName(String name) {
        return clientRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public Client findByCompanyName(String companyName) {
        return clientRepository.findByCompanyName(companyName)
                .orElse(null);
    }

    // updates cliente pessoa física
    // update nome
    @Transactional
    public Client updateClientName(String cpf, ClientDTO clientDTO) {
        // Encontrando um cliente pelo cpf
        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CPF:" + cpf));
        // atualizando campos do cliente
        client.setName(clientDTO.getName());
        return clientRepository.save(client);
    }

    // update tipo cliente
    @Transactional
    public Client updateClientType(String cpf, ClientDTO clientDTO) {
        Client clientType = clientRepository.findByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CPF:" + cpf));
        
        clientType.setClientType(clientDTO.getClientType());
        return clientRepository.save(clientType);
    }

    // update email
    @Transactional
    public Client updateClientEmail(String cpf, ClientDTO clientDTO) {
        Client clientEmail = clientRepository.findByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CPF:" + cpf));
        
        clientEmail.setEmail(clientDTO.getEmail());
        return clientRepository.save(clientEmail);
    }

    // update Profissao
    @Transactional
    public Client updateClientPosition(String cpf, ClientDTO clientDTO) {
        Client clientProfession = clientRepository.findByCpf(cpf)
            .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CPF:" + cpf));
        
        clientProfession.setProfession(clientDTO.getProfession());
        return clientRepository.save(clientProfession);
    }

    // updates cliente pessoa jurídica
    // update nome empresa
    @Transactional
    public Client updateClientCompany(String cnpj, ClientDTO clientDTO) {
        Client companyName = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CNPJ:" + cnpj));
        companyName.setName(clientDTO.getName());
        return clientRepository.save(companyName);
    }

    // update tipo cliente
    @Transactional
    public Client updateCompanyTypeClient(String cnpj, ClientDTO clientDTO) {
        Client typeClient = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CNPJ:" + cnpj));
        
        typeClient.setClientType(clientDTO.getClientType());
        return clientRepository.save(typeClient);
    }

    // update Email empresa
    @Transactional
    public Client updateCompanyEmail(String cnpj, ClientDTO clientDTO) {
        Client companyEmail = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CNPJ:" + cnpj));
        
        companyEmail.setEmail(clientDTO.getEmail());
        return clientRepository.save(companyEmail);
    }

    // update profissao
    @Transactional
    public Client updateCompanyProfession(String cnpj, ClientDTO clientDTO) {
        Client companyProfession = clientRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado por esse CNPJ:" + cnpj));
        
        companyProfession.setProfession(clientDTO.getProfession());
        return clientRepository.save(companyProfession);
    }// updates pessoas jurídicas

    
    // metodo deletar
    @Transactional
    public void deleteClient(String code) {
        clientRepository.deleteById(code);
    }



}
