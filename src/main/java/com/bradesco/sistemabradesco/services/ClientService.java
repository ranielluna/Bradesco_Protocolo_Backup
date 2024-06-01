package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.ClientDTO;
import com.bradesco.sistemabradesco.models.Client;
import com.bradesco.sistemabradesco.repository.ClientRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public  class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;

    /* metodo criar um novo cliente*/
    public Client addClient(ClientDTO clientDTO){
        Client newCliente = new Client();
        BeanUtils.copyProperties(clientDTO, newCliente);
        return clientRepository.save(newCliente);
    }

    /* metodo atualizar */


    // /*metodo salvar */
    // @Transactional
    // public Cliente salvarCliente(Cliente cliente){
    //     return clienteRepository.save(cliente);
    // }

    /* metodo deletar */
    @Transactional
    public void deleteClient(String code){
        clientRepository.deleteById(code);
    }


    /* Listar todos os clientes do Banco de dados */
    public List<Client> listClients(){
        return clientRepository.findAll();
    }

    /* metodos para encontrar cliente */
    @Transactional
    public Client findByCpf(String cpf){
        return clientRepository.findByCpf(cpf)
                   .orElse(null);
    }

    @Transactional
    public Client findByCnpj(String cnpj){
        return clientRepository.findByCnpj(cnpj)
                    .orElse(null);
    }

    @Transactional
    public Client findByName(String name){
        return clientRepository.findByName(name)
                     .orElse(null);
    }

    @Transactional
    public Client findByCompanyName(String companyName){
        return clientRepository.findByCompanyName(companyName)
                     .orElse(null);
    }
  
    
}   

