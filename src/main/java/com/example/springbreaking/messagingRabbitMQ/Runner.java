package com.example.springbreaking.messagingRabbitMQ;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;

/**
 * CommandLineRunner를 구현하고 run 메소드를 재구성해서 원하는 기능을 구현한 형태
 */
//@Component
public class Runner implements CommandLineRunner {

  private final RabbitTemplate rabbitTemplate;
  private final Receiver receiver;

  public Runner(Receiver receiver, RabbitTemplate rabbitTemplate) {
    this.receiver = receiver;
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Sending message...");

/*    // 토픽 - 라우팅키를 지정
    // foo.bar.baz 탬플릿을 사용하여 바인딩과 일치하는 라우팅키로 교환기로 메세지를 라우팅
    rabbitTemplate.convertAndSend(MessagingRabbitmqApplication.topicExchangeName, "foo.bar.baz", "Hello from RabbitMQ!");
    // 수신될 때까지 최대 10초 대기
    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);*/
  }

}