package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity

public class av_tipo1 {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
  @OneToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    private int ac_necessario;
    
    public av_tipo1(Long id, Quiz quiz, int ac_necessario) {
        this.id = id;
        this.quiz = quiz;
        this.ac_necessario = ac_necessario;
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
    public int getAc_necessario() {
        return ac_necessario;
    }
    public void setAc_necessario(int ac_necessario) {
        this.ac_necessario = ac_necessario;
    }
}
