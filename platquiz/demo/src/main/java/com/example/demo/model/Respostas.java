package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Respostas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "questao_id", nullable = false)
    private Questoes questao;  // Tipo deve ser 'Questoes', não 'Long'

    private String texto;
    private String img;
    private int valor;
    
    // Construtor ajustado para receber um objeto 'Questoes' em vez de 'Long'
    public Respostas(Questoes questao, String texto, String img, int valor) {
        this.questao = questao;
        this.texto = texto;
        this.img = img;
        this.valor = valor;
    }

    // Getters e setters corrigidos
    public Questoes getQuestao() {
        return questao;
    }

    public void setQuestao(Questoes questao) {
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

    public int getValor() {
        return valor;
    }

    public void getValor(int valor) {
        this.valor = valor;
    }
}
