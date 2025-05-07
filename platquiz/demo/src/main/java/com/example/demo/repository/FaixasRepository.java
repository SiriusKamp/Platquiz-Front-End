package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Faixas;
import com.example.demo.model.av_tipo3;
import com.example.demo.model.av_tipo4;

public interface FaixasRepository extends JpaRepository<Faixas, Long> {

    List<Faixas> findByavTipo3(av_tipo3 av3);

    List<Faixas> findByavTipo4(av_tipo4 av4);
}
