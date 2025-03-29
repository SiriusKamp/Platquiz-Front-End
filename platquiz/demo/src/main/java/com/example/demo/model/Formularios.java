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
    private String labels;
    private String campos;

    public Formularios(Quiz quiz, String cor_card, String labels, String campos) {
        this.quiz = quiz;
        this.cor_card = cor_card;
        this.labels = labels;
        this.campos = campos;
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
    public String getLabels() {
        return labels;
    }
    public void setLabels(String labels) {
        this.labels = labels;
    }
    public String getCampos() {
        return campos;
    }
    public void setCampos(String campos) {
        this.campos = campos;
    }

    
}

