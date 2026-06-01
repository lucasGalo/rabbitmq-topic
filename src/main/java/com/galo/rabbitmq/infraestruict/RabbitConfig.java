package com.galo.rabbitmq.infraestruict;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  // Constantes para nomes de exchange, filas e routing keys
  public static final String EXCHANGE_NAME = "pedido-exchange";
  public static final String QUEUE_NAME = "pedido-queue";
  public static final String DLQ_NAME = "pedido-dlq";
  public static final String ROUTING_KEY = "pedido.pagamento";

  // Exchange principal do tipo Topic
  @Bean
  TopicExchange exchange() {return new TopicExchange(EXCHANGE_NAME);}

  // Fila principal (durável) com configuração de Dead Letter
  @Bean
  Queue pedidoQueue() {
    return QueueBuilder.durable(QUEUE_NAME)
            // Se o consumidor rejeitar a mensagem, ela será enviada para o mesmo exchange
            .withArgument("x-dead-letter-exchange", EXCHANGE_NAME)
            // Routing key usada para redirecionar mensagens para a DLQ
            .withArgument("x-dead-letter-routing-key", "pedido.dlq")
            .build();
  }

  // Dead Letter Queue (DLQ) para armazenar mensagens rejeitadas
  @Bean
  Queue pedidoDlq() {return QueueBuilder.durable(DLQ_NAME).build();}

  // Binding da fila principal ao exchange usando a routing key de pagamento
  @Bean
  Binding pedidoBinding(Queue pedidoQueue, TopicExchange exchange) {return BindingBuilder.bind(pedidoQueue).to(exchange).with(ROUTING_KEY);}

  // Binding da DLQ ao exchange usando a routing key "pedido.dlq"
  @Bean
  Binding dlqBinding(Queue pedidoDlq, TopicExchange exchange) {return BindingBuilder.bind(pedidoDlq).to(exchange).with("pedido.dlq");}

  // Configuração do listener container
  // Por padrão, o Spring usa AcknowledgeMode.AUTO.
  // Aqui garantimos que mensagens rejeitadas não sejam reenfileiradas na fila original.
  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setDefaultRequeueRejected(false); // ESSENCIAL: envia para DLQ em caso de erro
    return factory;
  }
}
