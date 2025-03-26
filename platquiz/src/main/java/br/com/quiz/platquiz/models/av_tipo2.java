package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class av_tipo2 {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long quiz;
    private int porcent_necessario;
    
    public av_tipo2(Long id, Long quiz, int porcent_necessario) {
        this.id = id;
        this.quiz = quiz;
        this.porcent_necessario = porcent_necessario;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getQuiz() {
        return quiz;
    }
    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }
    public int getPorcent_necessario() {
        return porcent_necessario;
    }
    public void setPorcent_necessario(int porcent_necessario) {
        this.porcent_necessario = porcent_necessario;
    }
}
