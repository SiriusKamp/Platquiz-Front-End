package com.example.demo.model;


import jakarta.persistence.Column;
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
    @Column(unique = true, nullable = false)
    private String email;
    private String telefone;
    @Column(unique = true, nullable = false)
    private String registroAcademico;
    private String senha;
    private boolean ativo;
    
        // Construtor vazio (Hibernate precisa)
        public Usuario() {}
        
    public Usuario(String nome, String email, String contato, String registroAcademico, String senha, boolean ativo) {
        this.nome = nome;
        this.email = email;
        this.telefone = contato;
        this.registroAcademico = registroAcademico;
        this.senha = senha;
        this.ativo = ativo;
    }
    public Long getId() {
        return id;
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
    public String gettelefone() {
        return telefone;
    }
    public void settelefone(String contato) {
        this.telefone = contato;
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
