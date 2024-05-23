package com.bradesco.sistemabradesco.dto;

import com.bradesco.sistemabradesco.models.Position;

public class PositionDTO {
    private int codigo;
    private String cargo;

    public PositionDTO() {
        // Construtor padrão necessário para o Spring converter JSON em objeto
    }

    public PositionDTO(Position cargo) {
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
