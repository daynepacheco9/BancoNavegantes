package com.desktopapp.model;

import java.sql.Date;

import javax.persistence.*;
import javax.transaction.UserTransaction;

@Entity
@Table(name = "PagamentosData")
public class PagamentosData {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "Valor")
    private double valor;

    @ManyToOne
    @JoinColumn(name = "idCliente") // nome da coluna que será a foreign key
    private UserData idcliente;

    @ManyToOne
    @JoinColumn(name = "idDividendo") // nome da coluna que será a foreign key
    private UserData iddividendo;
    

    private boolean paid;

    public PagamentosData(){}

    public PagamentosData(String codigo, double valor, UserData idcliente, UserData iddividendo){
        this.codigo = codigo;
        this.valor = valor;
        this.idcliente = idcliente;
        this.iddividendo = iddividendo;
        this.paid = false;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public double getValor() {
        return valor;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }


    public UserData getIdcliente() {
        return idcliente;
    }


    public void setIdcliente(UserData idcliente) {
        this.idcliente = idcliente;
    }


    public UserData getIddividendo() {
        return iddividendo;
    }


    public void setIddividendo(UserData iddividendo) {
        this.iddividendo = iddividendo;
    }


    public boolean isPaidOut() {
        return paid;
    }


    public void setPaidOut(boolean paid) {
        this.paid = paid;
    }

    



}