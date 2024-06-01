package com.bradesco.sistemabradesco.dto;

import com.bradesco.sistemabradesco.models.Position;

public class PositionDTO {
    private int code;
    private String position;

    public PositionDTO() {
        // Construtor padrão necessário para o Spring converter JSON em objeto
    }

    public PositionDTO(Position position) {
        this.code = position.getCode();
        this.position = position.getPosition();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
