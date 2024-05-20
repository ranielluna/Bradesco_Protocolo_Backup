package com.bradesco.sistemabradesco.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bradesco.sistemabradesco.dto.ClienteDTO;
import com.bradesco.sistemabradesco.models.Cliente;
import com.bradesco.sistemabradesco.repository.ClienteRepository;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public  class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    // public ClienteService(ClienteRepository clienteRepository){
    //     this.clienteRepository=clienteRepository;
    // }

    /* metodo criar */
    public Cliente criarCliente(ClienteDTO clienteDTO){
        Cliente novoCliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, novoCliente);
        return clienteRepository.save(novoCliente);
    }

    /* metodo atualizar */


    // /*metodo salvar */
    // @Transactional
    // public Cliente salvarCliente(Cliente cliente){
    //     return clienteRepository.save(cliente);
    // }

    /* metodo deletar */
    @Transactional
    public void deletarCliente(String id){
        clienteRepository.deleteById(id);
    }


    /* Listar todos os clientes */
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    /* metodos para encontrar cliente */
    @Transactional
    public Cliente encontrarPorCpf(String cpf){
        return clienteRepository.findByCpf(cpf)
                   .orElse(null);
    }

    @Transactional
    public Cliente encontrarPorCnpj(String cnpj){
        return clienteRepository.findByCnpj(cnpj)
                    .orElse(null);
    }

    @Transactional
    public Cliente encontrarPorNome(String nome){
        return clienteRepository.findByNome(nome)
                     .orElse(null);
    }

    @Transactional
    public Cliente encontrarPorRazaoSocial(String razaoSocial){
        return clienteRepository.findByRazaoSocial(razaoSocial)
                     .orElse(null);
    }
  
    
}   

