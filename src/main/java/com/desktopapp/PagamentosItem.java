package com.desktopapp;

import com.desktopapp.model.PagamentosData;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PagamentosItem {

    public PagamentosItem(PagamentosData pagamento) {
        this.codigo = new SimpleStringProperty(this, "codigo");
        this.codigo.setValue(pagamento.getCodigo());

        this.nome = new SimpleStringProperty(this, "nome");
        this.nome.setValue(pagamento.getIddividendo().getUsername());

        this.valor = new SimpleDoubleProperty(this, "valor");
        this.valor.setValue(pagamento.getValor());

        this.paid = new SimpleStringProperty(this, "paid");
        this.setpaid(pagamento.getPaid());
    }

    private StringProperty codigo;
    private StringProperty nome;
    private DoubleProperty valor;
    private StringProperty paid;

    public String getCodigo() {
        return codigo.get();
    }
    public void setCodigo(String codigo) {
        this.codigo.setValue(codigo);
    }
    public String getNome() {
        return nome.get();
    }
    public void setNome(String nome) {
        this.nome.setValue(nome);
    }
    public Double getValor() {
        return valor.get();
    }
    public void setValor(Double valor) {
        this.valor.setValue(valor);
    }
    public void setpaid(boolean value) {
        this.paid.setValue(value ? "Pago" : "Não pago");
    }
    public String getPaid() {
        return paid.get();
    }
}
