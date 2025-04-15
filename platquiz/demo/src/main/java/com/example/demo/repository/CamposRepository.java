package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Campos;
import com.example.demo.model.Formularios;

public interface CamposRepository extends JpaRepository<Campos, Long> {
    <Optional> Campo findByFormulario(Formularios formulario);
    // Aqui você pode adicionar métodos personalizados, se necessário
    // Exemplo: List<Campo> findByTitulo(String titulo);

}
