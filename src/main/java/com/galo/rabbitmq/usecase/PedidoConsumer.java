package com.galo.rabbitmq.usecase;

import com.galo.rabbitmq.infraestruict.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumer {

  @RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
  public void receberPedido1(String mensagem) {
    System.out.println("Mensagem recebida 1 # :" + mensagem);
    if (mensagem.contains("3")) {
      throw new RuntimeException("Erro ao processar pedido: " + mensagem);
    }
  }

  @RabbitListener(queues = RabbitConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
  public void receberPedido2(String mensagem) {
    System.out.println("Mensagem recebida 2 # : " + mensagem);
    if (mensagem.contains("3")) {
      throw new RuntimeException("Erro ao processar pedido: " + mensagem);
    }
  }

  @RabbitListener(queues = RabbitConfig.DLQ_NAME, containerFactory = "rabbitListenerContainerFactory")
  public void receberDlq(String mensagem) {
    System.out.println("Mensagem enviada para DLQ: " + mensagem);
  }
}
