package com.example.demo.model;


import jakarta.persistence.*;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professorid", nullable = false)
    private Usuario professor;  // Relacionamento com a tabela 'usuarios'

    private String nome;
    private String cor_fundo;
    private int n_perguntas;
    private boolean r_escrita;
    private String materias;
    private int tipo_avaliacao;
    private String cor_feedback;

    public Quiz() {}

    public Quiz(Usuario professor, String nome, String cor_fundo, int n_perguntas, boolean r_escrita, 
                String materias, int tipo_avaliacao, String cor_feedback) {
        this.professor = professor;
        this.nome = nome;
        this.cor_fundo = cor_fundo;
        this.n_perguntas = n_perguntas;
        this.r_escrita = r_escrita;
        this.materias = materias;
        this.tipo_avaliacao = tipo_avaliacao;
        this.cor_feedback = cor_feedback;
    }

    public Long getId() {
        return id;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCor_fundo() {
        return cor_fundo;
    }

    public void setCor_fundo(String cor_fundo) {
        this.cor_fundo = cor_fundo;
    }

    public int getN_perguntas() {
        return n_perguntas;
    }

    public void setN_perguntas(int n_perguntas) {
        this.n_perguntas = n_perguntas;
    }

    public boolean isR_escrita() {
        return r_escrita;
    }

    public void setR_escrita(boolean r_escrita) {
        this.r_escrita = r_escrita;
    }

    public String getMaterias() {
        return materias;
    }

    public void setMaterias(String materias) {
        this.materias = materias;
    }

    public int getTipo_avaliacao() {
        return tipo_avaliacao;
    }

    public void setTipo_avaliacao(int tipo_avaliacao) {
        this.tipo_avaliacao = tipo_avaliacao;
    }

    public String getCor_feedback() {
        return cor_feedback;
    }

    public void setCor_feedback(String cor_feedback) {
        this.cor_feedback = cor_feedback;
    }
}
