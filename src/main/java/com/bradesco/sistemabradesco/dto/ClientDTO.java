package com.bradesco.sistemabradesco.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.BeanUtils;

import com.bradesco.sistemabradesco.models.Client;

@Schema(description = "Dados para criar um cliente")
public class ClientDTO{

    @Schema(description = "Cpf do cliente", example = "71002856410")
    private String cpf;

    @Schema(description = "Nome do cliente", example = "Amália Sobral")
    private String name;

    @Schema(description = "Email do cliente", example = "ama.sobral@gmail.com")
    private String email;

    @Schema(description = "Profissão do cliente", example = "Ceramista")
    private String profession;

    @Schema(description = "Tipo do cliente", example = "Pessoa física")
    private String clientType;

    @Schema(description = "Cnpj do cliente", example = "33.123456.0001-80")
    private String cnpj;

    @Schema(description = "Nome da empresa", example = "AMA Cerâmicas Ltda. ")
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