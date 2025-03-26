package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class av_tipo1 {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long quiz;
    private int ac_necessario;
    
    public av_tipo1(Long id, Long quiz, int ac_necessario) {
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
    public Long getQuiz() {
        return quiz;
    }
    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }
    public int getAc_necessario() {
        return ac_necessario;
    }
    public void setAc_necessario(int ac_necessario) {
        this.ac_necessario = ac_necessario;
    }
}
