package com.goesbernardo.order_management.config;

import com.goesbernardo.order_management.model.Pedido;
import com.goesbernardo.order_management.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumer {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private KafkaTemplate<String, Pedido> kafkaTemplate;

    @KafkaListener(topics = "${spring.kafka.topic.pedido-recebido}", groupId = "pedido-consumer-group")
    public void consumirPedido(Pedido pedido) {
        // Processa o pedido
        Pedido pedidoProcessado = pedidoService.processarPedido(pedido);

        // Envia o pedido processado ao próximo tópico
        kafkaTemplate.send("pedido-processado", pedidoProcessado.getId(), pedidoProcessado);
        System.out.println("Pedido processado e enviado: " + pedidoProcessado);
    }
}
