package com.goesbernardo.order_management.service;

import com.goesbernardo.order_management.model.Pedido;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    public Pedido processarPedido(Pedido pedido) {
        // 1. Calcular o valor total
        double valorTotal = pedido.calcularValorTotal();
        pedido.setValorTotal(valorTotal);

        // 2. Aplicar desconto se o valor total for maior que um limite (exemplo: R$ 500)
        if (valorTotal > 500) {
            pedido.aplicarDesconto(10); // 10% de desconto
        }

        // 3. Aplicar uma taxa de serviço (exemplo: 5% de taxa)
        pedido.aplicarTaxa(5);

        // 4. Calcular o valor final
        pedido.calcularValorFinal();

        return pedido;
    }
}
