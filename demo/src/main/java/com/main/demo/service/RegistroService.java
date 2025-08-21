package com.main.demo.service;

import com.main.demo.model.dto.RegistroCreateRequest;
import com.main.demo.model.dto.RegistroResponse;
import java.util.List;

public interface RegistroService {
    RegistroResponse save(RegistroCreateRequest request);
    List<RegistroResponse> findAll();
    RegistroResponse findById(Long id);
    RegistroResponse update(Long id, RegistroCreateRequest request);
    void deleteById(Long id);
}
