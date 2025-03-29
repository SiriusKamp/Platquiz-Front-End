package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class av_tipo4 {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

         @OneToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    private String cod_porcent;
    
    public av_tipo4(Long id, Quiz quiz, String cod_porcent) {
        this.id = id;
        this.quiz = quiz;
        this.cod_porcent = cod_porcent;
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
    public String getCod_porcent() {
        return cod_porcent;
    }
    public void setCod_porcent(String cod_porcent) {
        this.cod_porcent = cod_porcent;
    }
}
