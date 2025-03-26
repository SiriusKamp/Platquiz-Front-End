package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Formularios {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long quiz;
    private String cor_card;
    private String labels;
    private String campos;

    public Formularios(Long quiz, String cor_card, String labels, String campos) {
        this.quiz = quiz;
        this.cor_card = cor_card;
        this.labels = labels;
        this.campos = campos;
    }

    public Long getQuiz() {
        return quiz;
    }
    public void setQuiz(Long quiz) {
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

