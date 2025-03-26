package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Respostas {
    @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private Long questao;
    private String texto;
    private String img;
    private String tipo;

    public Respostas(Long questao, String texto, String img, String tipo) {
        this.questao = questao;
        this.texto = texto;
        this.img = img;
        this.tipo = tipo;
    }

    public Long getQuestao() {
        return questao;
    }

    public void setQuestao(Long questao) {
        this.questao = questao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
