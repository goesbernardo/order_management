package com.goesbernardo.order_management.model;

import lombok.Data;

@Data
public class ItemPedido {

    private String nomeProduto;
    private double preco;
    private int quantidade;
}
