package com.main.demo.repository;

import com.main.demo.model.entity.Registro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findAllByOrderByIdDesc();


}
