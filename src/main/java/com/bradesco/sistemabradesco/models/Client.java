package com.bradesco.sistemabradesco.models;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.dto.ClientDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Client implements Serializable {
  @Id
  // @Column(name = "cpf")
  private String cpf;

  @Column(name = "nome", length = 60, nullable = false)
  private String name;

  @Column(name = "email", length = 60, unique = true)
  private String email;

  @Column(name = "profissao", length = 60)
  private String profession;

  @Column(name = "tipo_cliente", nullable = false)
  private String clientType;

  @Column(name = "cnpj", length = 20)
  private String cnpj;

  @Column(name = "razao_social", length = 255)
  private String companyName;



  public Client() {
  }

  public Client(ClientDTO clientDTO){
    BeanUtils.copyProperties(clientDTO, this);
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

  //METODOS HASHCODE E EQUALS
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    return result;
  }// Este método é responsável por calcular o código de hash do objeto.

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Client other = (Client) obj;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    return true;
  }// Este método verifica se dois objetos são iguais


  
}//class
