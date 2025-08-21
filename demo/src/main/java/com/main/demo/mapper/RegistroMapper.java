package com.main.demo.mapper;

import com.main.demo.model.dto.RegistroCreateRequest;
import com.main.demo.model.dto.RegistroResponse;
import com.main.demo.model.entity.Registro;
import org.springframework.stereotype.Component;

@Component
public class RegistroMapper {
    public Registro toRegistro(RegistroCreateRequest request) {
        return Registro.builder()
                .ot(request.getOt())
                .cliente(request.getCliente())
                .equipos(request.getEquipos())
                .metrologo(request.getMetrologo())
                .firmaPath(request.getFirmaPath())
                .estado(request.getEstado())
                .fechaHora(request.getFechaHora())
                .build();
    }

    public RegistroResponse toRegistroResponse(Registro registro) {
        return RegistroResponse.builder()
                .id(registro.getId())
                .ot(registro.getOt())
                .cliente(registro.getCliente())
                .equipos(registro.getEquipos())
                .metrologo(registro.getMetrologo())
                .firmaPath(registro.getFirmaPath())
                .estado(registro.getEstado())
                .fechaHora(registro.getFechaHora())
                .build();
    }

    public void updateRegistroFromRequest(Registro registro, RegistroCreateRequest request) {
        registro.setOt(request.getOt());
        registro.setCliente(request.getCliente());
        registro.setEquipos(request.getEquipos());
        registro.setMetrologo(request.getMetrologo());
        registro.setFirmaPath(request.getFirmaPath());
        registro.setEstado(request.getEstado());
        registro.setFechaHora(request.getFechaHora());
    }
}
