//package com.example.springbreaking.messagingWebSocketStomp;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
///**
// * 메세지 브로커의 역할 : 생산자의 메세지를 받아 수신자에게 전달
// * 비동기 메세징 프로토콜을 이용해 서로 다른 애플리케이션, 서비스를 중개합니다.
// *
// * 주요 기능
// *
// * - 디 커플링
// *   서로의 의존성을 제거해 영향을 미치지 않습니다.
// *
// * - 메세지 캐싱
// *   브로커는 메세지를 전달하기전 잠시 저장하고 있어서 수신자가 받을 상태가 되면 전달합니다.
// *
// * - 스케일 아웃
// *   트래픽이 증가하면 브로커는 수평적으로 확장합니다.
// *
// * - 라우팅
// *   특정 규칙이나 패턴을 통해 라우팅을 기능을 제공합니다.
// *
// * - 신뢰성
// *   메세지의 persistence, 트랜잭션, 중복방지등의 기능
// *
// * - 다양한 프토토콜 지원
// *   MQTT, AMQP, STOMP등..
// *
// * 일반적으로 사용되는 메세지 브로커는 RabbitMQ, Apache Kafka, ActiveMQ, MQTT
// *
// */
//@Configuration
//@EnableWebSocketMessageBroker // 메세지 브로커 활성화
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//  @Override
//  public void configureMessageBroker(MessageBrokerRegistry config) {
//    // 브로커 활성화 + 브로드캐스팅 -> 클라이언트는 이 주소를 구독해 메세지를 수신
//    config.enableSimpleBroker("/topic");
//    // 서버로 메세지를 보낼 주소 접두사 -> /app/hello로 보내면 `@MessageMapping("/hello")`가 처리한다.
//    config.setApplicationDestinationPrefixes("/app");
//  }
//
//  @Override
//  public void registerStompEndpoints(StompEndpointRegistry registry) {
//    // 엔드포인트 - 클라이언트가 웹소켓을 연결할 때 사용
//    registry.addEndpoint("/gs-guide-websocket");
//  }
//
//}