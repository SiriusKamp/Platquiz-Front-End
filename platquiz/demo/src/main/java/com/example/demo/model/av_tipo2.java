package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity

public class av_tipo2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    private int porcent_necessario;

    public av_tipo2(Long id, Quiz quiz, int porcent_necessario) {
        this.id = id;
        this.quiz = quiz;
        this.porcent_necessario = porcent_necessario;
    }

    public av_tipo2() {
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

    public int getPorcent_necessario() {
        return porcent_necessario;
    }

    public void setPorcent_necessario(int porcent_necessario) {
        this.porcent_necessario = porcent_necessario;
    }
}
