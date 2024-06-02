package com.bradesco.sistemabradesco.dto;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Client;


public class ClientDTO{
    private String cpf;
    private String name;
    private String email;
    private String profession;
    private String clientType;
    private String cnpj;
    private String companyName; //razaoSocial

    public ClientDTO(Client client){
        BeanUtils.copyProperties(client, this);
    }

    public ClientDTO(){
      
    }
    
    //GETTERS E SETTERS
    public String getCpf() {
      return cpf;
    }

    public void setCpf(String cpf) {
      this.cpf = cpf;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getProfession() {
      return profession;
    }

    public void setProfession(String profession) {
      this.profession = profession;
    }

    public String getClientType() {
      return clientType;
    }

    public void setClientType(String clientType) {
      this.clientType = clientType;
    }

    public String getCnpj() {
      return cnpj;
    }

    public void setCnpj(String cnpj) {
      this.cnpj = cnpj;
    }

    public String getCompanyName() {
      return companyName;
    }

    public void setCompanyName(String companyName) {
      this.companyName = companyName;
    }

  
}