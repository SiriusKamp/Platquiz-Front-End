package com.example.demo.DTOs;

import java.util.List;

import lombok.Data;

@Data
public class AvaliacaoRequest {

    private int tipo;
    private Long quizId;
    private Integer valor; // Para tipo 1 e 2
    private List<Faixa> faixas; // Para tipo 3 e 4

    public int getTipo() {
        return tipo;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }

    public List<Faixa> getFaixas() {
        return faixas;
    }
}
