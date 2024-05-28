package com.desktopapp.model;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "PagamentosData")
public class PagamentosData {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "Codigo")
    private long codigo;
    @Column(name = "Valor")
    private double valor;
    @Column(name = "IdCliente")
    private long idcliente;
    @Column(name = "IdDevidendo")
    private long iddevidendo;
    

    private boolean pag;


}