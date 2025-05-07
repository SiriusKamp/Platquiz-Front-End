package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Quiz;
import com.example.demo.model.av_tipo4;

public interface av_tipo4Repository extends JpaRepository<av_tipo4, Long> {

    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<av_tipo4> findByTitulo(String titulo);
    Optional<av_tipo4> findByquiz(Quiz quiz);

}
