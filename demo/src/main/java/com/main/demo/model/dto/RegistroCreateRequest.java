package com.main.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Builder
public class RegistroCreateRequest {
    @NotEmpty(message = "El campo OT no puede ser vacío o nulo.")
    private String ot;

    @NotEmpty(message = "El campo Cliente no puede ser vacío o nulo.")
    private String cliente;

    private String equipos;

    @NotEmpty(message = "El campo Metrologo no puede ser vacío o nulo.")
    private String metrologo;

    private String firmaPath;
    private Boolean estado;
    private LocalDateTime fechaHora;
}
