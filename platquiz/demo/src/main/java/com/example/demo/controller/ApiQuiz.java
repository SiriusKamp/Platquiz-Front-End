package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTOs.QuizDTO;
import com.example.demo.model.Quiz;
import com.example.demo.repository.QuizRepository;

@RestController
public class ApiQuiz {
    
    @Autowired
    QuizRepository quizRepository;


    @GetMapping("/api/quizzes/{id}")
@ResponseBody
public Quiz buscarQuiz(@PathVariable String id) {
    Optional<Quiz> quiz = quizRepository.findById(Long.parseLong(id));
    if (quiz.get() == null) return null;
    if(quiz.get().getMaterias() == null){
        quiz.get().setMaterias("");
    }
    return quiz.get(); // Crie um construtor ou conversor para isso
}

}
