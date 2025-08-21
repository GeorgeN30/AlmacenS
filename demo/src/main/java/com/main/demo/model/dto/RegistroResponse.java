package com.main.demo.model.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaHora;
}
