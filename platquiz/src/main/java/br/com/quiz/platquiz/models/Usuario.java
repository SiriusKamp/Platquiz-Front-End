package br.com.quiz.platquiz.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

    private String nome;
    private String email;
    private String contato;
    private String registroAcademico;
    private String senha;
    private boolean ativo;
    
    public Usuario(String nome, String email, String contato, String registroAcademico, String senha, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.contato = contato;
        this.registroAcademico = registroAcademico;
        this.senha = senha;
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public String getRegistroAcademico() {
        return registroAcademico;
    }
    public void setRegistroAcademico(String registroAcademico) {
        this.registroAcademico = registroAcademico;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
