package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class av_tipo4 {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long quiz;
    private String cod_porcent;
    
    public av_tipo4(Long id, Long quiz, String cod_porcent) {
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
    public Long getQuiz() {
        return quiz;
    }
    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }
    public String getCod_porcent() {
        return cod_porcent;
    }
    public void setCod_porcent(String cod_porcent) {
        this.cod_porcent = cod_porcent;
    }
}
