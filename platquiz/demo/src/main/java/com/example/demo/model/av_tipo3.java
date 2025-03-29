package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class av_tipo3 {

@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
   @OneToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    private String cod_acertos;
    public av_tipo3(Long id, Quiz quiz, String cod_acertos) {
        this.id = id;
        this.quiz = quiz;
        this.cod_acertos = cod_acertos;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Quiz getQuiz() {
        return quiz;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public String getCod_acertos() {
        return cod_acertos;
    }
    public void setCod_acertos(String cod_acertos) {
        this.cod_acertos = cod_acertos;
    }
}
