package com.galo.rabbitmq.usecase;

import com.galo.rabbitmq.infraestruict.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PedidoProducer {

  private final RabbitTemplate rabbitTemplate;
  private int contador = 0;

  public PedidoProducer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Scheduled(fixedRate = 5000)
  public void enviarPedido() {
    String mensagem = "Pedido 1 # " + (++contador);
    rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE_NAME,
            RabbitConfig.ROUTING_KEY,
            mensagem
    );
    System.out.println("Mensagem enviada: " + mensagem);
  }

  @Scheduled(fixedRate = 2000)
  public void enviarPedido2() {
    String mensagem = "Pedido 2 # " + (++contador);
    rabbitTemplate.convertAndSend(
            RabbitConfig.EXCHANGE_NAME,
            RabbitConfig.ROUTING_KEY,
            mensagem
    );
    System.out.println("Mensagem enviada: " + mensagem);
  }
}
