package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Faixas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "av_tipo3", nullable = true)
    private av_tipo3 avTipo3;

    @ManyToOne
    @JoinColumn(name = "av_tipo4", nullable = true)
    private av_tipo4 avTipo4;

    public int de;
    public int ate;
    public String msg;

    public Faixas(Long id, av_tipo3 av_tipo3, av_tipo4 av_tipo4, int de,
            int ate, String msg) {
        this.id = id;
        this.avTipo3 = av_tipo3;
        this.avTipo4 = av_tipo4;
        this.de = de;
        this.ate = ate;
        this.msg = msg;
    }

    public Faixas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public av_tipo3 getAv_tipo3() {
        return avTipo3;
    }

    public void setAv_tipo3(av_tipo3 av_tipo3) {
        this.avTipo3 = av_tipo3;
    }

    public av_tipo4 getAv_tipo4() {
        return avTipo4;
    }

    public void setAv_tipo4(av_tipo4 av_tipo4) {
        this.avTipo4 = av_tipo4;
    }

    public int getDe() {
        return de;
    }

    public void setDe(int de) {
        this.de = de;
    }

    public int getAte() {
        return ate;
    }

    public void setAte(int ate) {
        this.ate = ate;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
