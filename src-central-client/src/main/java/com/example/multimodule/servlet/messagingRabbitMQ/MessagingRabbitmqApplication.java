package com.example.multimodule.servlet.messagingRabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

/**
 * SpringbreakingApplication 는 주석 처리 바람
 *
 */
//@SpringBootApplication
public class MessagingRabbitmqApplication {

  static final String topicExchangeName = "spring-boot-exchange";

  static final String queueName = "spring-boot";

  /**
   * 대기열 큐 생성
   * durable: false -> 지속되지 않음
   * @return
   */
  @Bean
  Queue queue() {
    return new Queue(queueName, false);
  }

  /**
   * 토픽 체인지 생성 반환, 교환기의 이름은 spring-boot-exchange
   * @return
   */
  @Bean
  TopicExchange exchange() {
    return new TopicExchange(topicExchangeName);
  }

  /**
   * 큐와 교환기를 바인딩 -> foo.bar.# 로 시작하는 라우팅 키로 전송된 메세지를 큐에 라우팅
   * @param queue
   * @param exchange
   * @return
   */
  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
  }

  /**
   * 메세지 리스너 컨테이너 생성 - 큐에서 메세지를 수신하고 처리
   * @param connectionFactory
   * @param listenerAdapter
   * @return
   */
  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
      MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  /**
   * 메세지 어댑터 - Receiver 객체를 사용하여 리스너 어댑터 생성
   * Receiver가 실제 메세지 처리 로직을 포함
   * @param receiver
   * @return
   */
  @Bean
  MessageListenerAdapter listenerAdapter(Receiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(MessagingRabbitmqApplication.class, args).close(); // close : RabbitMQ와 연결 종료
  }

}