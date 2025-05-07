package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Quiz;
import com.example.demo.model.av_tipo3;

public interface av_tipo3Repository extends JpaRepository<av_tipo3, Long> {

    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<av_tipo3> findByTitulo(String titulo);
    Optional<av_tipo3> findByquiz(Quiz quiz);

}
