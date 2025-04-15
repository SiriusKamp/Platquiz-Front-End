package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Formularios;
import com.example.demo.model.Quiz;

public interface FormulariosRepository extends JpaRepository<Formularios, Long> {
    Optional<Formularios> findByquiz(Quiz quiz);
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Form> findByTitulo(String titulo);

}
