package com.desktopapp.model;

import javax.persistence.*;

@Entity
@Table(name = "PagamentosData")
public class PagamentosData {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "Codigo")
    private String codigo;
    @Column(name = "Valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "idCliente") // nome da coluna que será a foreign key
    private UserData idcliente;

    @ManyToOne
    @JoinColumn(name = "idDividendo") // nome da coluna que será a foreign key
    private UserData iddividendo;
    

    private boolean paid;

    public PagamentosData(){}

    public PagamentosData(String codigo, Double valor, UserData idcliente, UserData iddividendo){
        this.codigo = codigo;
        this.valor = valor;
        this.idcliente = idcliente;
        this.iddividendo = iddividendo;
        this.paid = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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


    public boolean getPaid() {
        return paid;
    }


    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    
    

}