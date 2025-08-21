package com.main.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ot;

    @Column(nullable = false)
    private String cliente;

    @Column(length = 2000 , nullable = true)
    private String equipos;

    @Column(nullable = false)
    private String metrologo;

    private String firmaPath;

    @Builder.Default
    private Boolean estado = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

}
