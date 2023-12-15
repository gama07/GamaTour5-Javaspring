package com.gamatour.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPassagem;

    private LocalDate dataPartida;
    private LocalDate dataRetorno;
    private int numPassageiros;

    @ManyToOne
    @JoinColumn(name = "cliente_id") 
    private Cliente cliente;

    @ManyToOne
    private Destino destino;

    private float precoPassagem; 

    public Long getIdPassagem() {
        return idPassagem;
    }

    public void setIdPassagem(Long idPassagem) {
        this.idPassagem = idPassagem;
    }

    public LocalDate getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDate dataPartida) {
        this.dataPartida = dataPartida;
    }

    public LocalDate getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(LocalDate dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public int getNumPassageiros() {
        return numPassageiros;
    }

    public void setNumPassageiros(int numPassageiros) {
        this.numPassageiros = numPassageiros;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public float getPrecoPassagem() {
        return precoPassagem;
    }

    public void setPrecoPassagem(float precoPassagem) {
        this.precoPassagem = precoPassagem;
    }
}
