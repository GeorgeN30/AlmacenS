package com.main.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class RegistroResponse {
    private Long id;
    private String ot;
    private String cliente;
    private String equipos;
    private String metrologo;
    private String firmaPath;
    private Boolean estado;
    private LocalDateTime fechaHora;
}
