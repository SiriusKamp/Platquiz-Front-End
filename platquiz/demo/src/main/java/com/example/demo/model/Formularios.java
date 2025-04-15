package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Formularios {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
    
        @OneToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    private String cor_card;


    public Formularios(Quiz quiz, String cor_card) {
        this.quiz = quiz;
        this.cor_card = cor_card;

    }

    public Quiz getQuiz() {
        return quiz;
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public String getCor_card() {
        return cor_card;
    }
    public void setCor_card(String cor_card) {
        this.cor_card = cor_card;
    }

    
}

