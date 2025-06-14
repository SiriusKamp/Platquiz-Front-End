package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Quiz;
import com.example.demo.model.Usuario;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    Optional<Quiz> findByNome(String nome);
    Optional<Quiz> findById(Long id);
    List<Quiz> findByprofessor(Usuario user);

    Quiz findTopByOrderByIdDesc();
 
    
} 