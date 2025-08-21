package com.main.demo.controller;

import com.main.demo.model.dto.RegistroCreateRequest;
import com.main.demo.model.dto.RegistroResponse;
import com.main.demo.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @GetMapping
    public List<RegistroResponse> getAll() {
        return registroService.findAll();
    }

    @GetMapping("/{id}")
    public RegistroResponse getById(@PathVariable Long id) {
        return registroService.findById(id);
    }

    @PostMapping
    public RegistroResponse create(@RequestBody RegistroCreateRequest request) {
        return registroService.save(request);
    }

    @PutMapping("/{id}")
    public RegistroResponse update(@PathVariable Long id, @RequestBody RegistroCreateRequest request) {
        return registroService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        registroService.deleteById(id);
    }
}
