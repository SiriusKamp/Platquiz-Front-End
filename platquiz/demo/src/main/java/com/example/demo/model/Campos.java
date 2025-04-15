package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Campos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
       
           @OneToOne
       @JoinColumn(name = "form_id", nullable = false)
       private Formularios formulario;
       private String label;
       private String campo;

       public Campos(Formularios formulario, String label, String campo) {
        this.formulario = formulario;
        this.label = label;
        this.campo = campo;
    }
       public Long getId() {
           return id;
       }
       public void setId(Long id) {
           this.id = id;
       }
       public Formularios getFormulario() {
           return formulario;
       }
       public void setFormulario(Formularios formulario) {
           this.formulario = formulario;
       }
       public String getLabel() {
           return label;
       }
       public void setLabel(String label) {
           this.label = label;
       }
       public String getCampo() {
           return campo;
       }
       public void setCampo(String campo) {
           this.campo = campo;
       }
}
