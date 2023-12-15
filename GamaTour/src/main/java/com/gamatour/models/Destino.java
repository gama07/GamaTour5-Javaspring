package com.gamatour.models;

import jakarta.persistence.*;

@Entity
public class Destino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDestino;

    private String nomeDestino;

    public Long getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(Long idDestino) {
        this.idDestino = idDestino;
    }

    public String getNomeDestino() {
        return nomeDestino;
    }

    public void setNomeDestino(String nomeDestino) {
        this.nomeDestino = nomeDestino;
    }
}
