package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Questoes {
   @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long quiz;
    private String t_pergunta;
    private  String img_pergunta;
    private  String cor_card;
    private  int n_respostas;
    private  String img_bproximo;
    private  String img_banterior;
    private  String cor_bproximo;
    private  String cor_banterior;
    
    public Questoes(Long quiz, String t_pergunta, String img_pergunta, String cor_card, int n_respostas,
    String img_bproximo, String img_banterior, String cor_bproximo, String cor_banterior) {
this.quiz = quiz;
this.t_pergunta = t_pergunta;
this.img_pergunta = img_pergunta;
this.cor_card = cor_card;
this.n_respostas = n_respostas;
this.img_bproximo = img_bproximo;
this.img_banterior = img_banterior;
this.cor_bproximo = cor_bproximo;
this.cor_banterior = cor_banterior;
}

    public Long getQuiz() {
        return quiz;
    }
    public void setQuiz(Long quiz) {
        this.quiz = quiz;
    }
    public String getT_pergunta() {
        return t_pergunta;
    }
    public void setT_pergunta(String t_pergunta) {
        this.t_pergunta = t_pergunta;
    }
    public String getImg_pergunta() {
        return img_pergunta;
    }
    public void setImg_pergunta(String img_pergunta) {
        this.img_pergunta = img_pergunta;
    }
    public String getCor_card() {
        return cor_card;
    }
    public void setCor_card(String cor_card) {
        this.cor_card = cor_card;
    }
    public int getN_respostas() {
        return n_respostas;
    }
    public void setN_respostas(int n_respostas) {
        this.n_respostas = n_respostas;
    }
    public String getImg_bproximo() {
        return img_bproximo;
    }
    public void setImg_bproximo(String img_bproximo) {
        this.img_bproximo = img_bproximo;
    }
    public String getImg_banterior() {
        return img_banterior;
    }
    public void setImg_banterior(String img_banterior) {
        this.img_banterior = img_banterior;
    }
    public String getCor_bproximo() {
        return cor_bproximo;
    }
    public void setCor_bproximo(String cor_bproximo) {
        this.cor_bproximo = cor_bproximo;
    }
    public String getCor_banterior() {
        return cor_banterior;
    }
    public void setCor_banterior(String cor_banterior) {
        this.cor_banterior = cor_banterior;
    }
}
