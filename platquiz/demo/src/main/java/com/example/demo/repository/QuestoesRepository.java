package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Questoes;
import com.example.demo.model.Quiz;

public interface QuestoesRepository extends JpaRepository<Questoes, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find questions by a specific field or criteria
    List<Questoes> findByQuiz(Quiz quiz);

}
