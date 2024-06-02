package com.bradesco.sistemabradesco.dto;

import com.bradesco.sistemabradesco.models.Position;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados para criar um cargo")
public class PositionDTO {
    private int code;

    @Schema(description = "Nome do cargo", example = "Operador")
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
