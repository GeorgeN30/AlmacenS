package com.main.demo.service;

import com.main.demo.exception.RegistroNotFoundException;
import com.main.demo.mapper.RegistroMapper;
import com.main.demo.model.dto.RegistroCreateRequest;
import com.main.demo.model.dto.RegistroResponse;
import com.main.demo.model.entity.Registro;
import com.main.demo.repository.RegistroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroServiceImpl implements RegistroService {

    private final RegistroRepository registroRepository;
    private final RegistroMapper registroMapper;

    @Override
    public RegistroResponse save(RegistroCreateRequest request) {
        Registro registro = registroMapper.toRegistro(request);
        registro.setFechaHora(LocalDateTime.now(ZoneId.systemDefault()));
        return registroMapper.toRegistroResponse(registroRepository.save(registro));
    }

    @Override
    public List<RegistroResponse> findAll() {
        return registroRepository.findAllByOrderByIdDesc()
                .stream()
                .map(registroMapper::toRegistroResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RegistroResponse findById(Long id) {
        return registroRepository.findById(id)
                .map(registroMapper::toRegistroResponse)
                .orElseThrow(RegistroNotFoundException::new);
    }

    @Override
    public RegistroResponse update(Long id, RegistroCreateRequest request) {
        return registroRepository.findById(id)
                .map(registro -> {
                    registroMapper.updateRegistroFromRequest(registro, request);
                    return registroRepository.save(registro);
                })
                .map(registroMapper::toRegistroResponse)
                .orElseThrow(RegistroNotFoundException::new);
    }

    @Override
    public void deleteById(Long id) {
        if (registroRepository.findById(id).isEmpty()) {
            throw new RegistroNotFoundException();
        }
        registroRepository.deleteById(id);
    }
}
