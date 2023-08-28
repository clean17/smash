package com.example.springbreaking.messagingRabbitMQ;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Receiver의 메세지 수신 방법을 정의하는 POJO
 */
@Component
public class Receiver {

  /**
   * 동시성 유틸리티 클래스 - 다른 스레드의 작업 완료를 기다림
   * 카운트가 0 이되면 await중인 스레드가 실행된다.
   * 일회성으로 사용되며 재사용될 수 없다.
   * 이러한 방법을 이용해 여러 스레드가 동시에 진행되도록 의도할 수 있다.
   * 프로덕션에서는 사실상 쓰이지 않는다.
   */
  private CountDownLatch latch = new CountDownLatch(1);

  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
    latch.countDown(); // 0 -> 실행
  }

  public CountDownLatch getLatch() {
    return latch;
  }

}