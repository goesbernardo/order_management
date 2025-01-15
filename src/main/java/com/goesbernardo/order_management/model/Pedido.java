package com.goesbernardo.order_management.model;

import lombok.Data;

import java.util.List;

@Data
public class Pedido {

    private String id;
    private List<ItemPedido> itens;
    private double valorTotal;
    private double desconto;
    private double taxa;
    private double valorFinal;

    public double calcularValorTotal() {
        return itens.stream().mapToDouble(item -> item.getPreco() * item.getQuantidade()).sum();
    }

    public void aplicarDesconto(double percentual) {
        this.desconto = valorTotal * (percentual / 100);
    }

    public void aplicarTaxa(double percentual) {
        this.taxa = valorTotal * (percentual / 100);
    }

    public void calcularValorFinal() {
        this.valorFinal = valorTotal - desconto + taxa;
    }
}
