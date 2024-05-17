package com.bradesco.sistemabradesco.dto;

import com.bradesco.sistemabradesco.models.Cargo;

public class CargoDTO {
    private int codigo;
    private String cargo;

    public CargoDTO() {
        // Construtor padrão necessário para o Spring converter JSON em objeto
    }

    public CargoDTO(Cargo cargo) {
        this.codigo = cargo.getCodigo();
        this.cargo = cargo.getCargo();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
