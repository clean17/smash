//package com.example.springbreaking.reactiveWeb;
//
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//
//@SpringBootApplication
//public class ReactiveWebServiceApplication {
//
//	public static void main(String[] args) {
//		ConfigurableApplicationContext context = SpringApplication.run(ReactiveWebServiceApplication.class, args);
//		GreetingClient greetingClient = context.getBean(GreetingClient.class);
//
//		// We need to block for the content here or the JVM might exit before the message is logged
//		// 메세지를 기록하기 전에 JVM이 종료된다 ?
//		// block()은 웬만하면 사용하지 않는게 좋다 ? 차리리 비동기 처리를 해라 ?
//		System.out.println(">> message = " + greetingClient.getMessage().block());
//		/*
//		  응답 결과가 MonoMapFuseable로 나온다면 ?
//		  -> 스트림을 처리하거나 변환하는 과정에서 잘못된 로깅 또는 출력이 있었을 가능성
//		  .block() 메서드를 사용하거나 반응형 방식으로 데이터를 구독하고 출력해야 합니다.
//		  .block()은 반응형 프로그래밍 패러다임에 어긋나는 방식이므로, 가능한 한 피해야 합니다. 대신, 반응형 연산자와 구독을 사용하여 데이터 처리를 완료하세요.
//          .block() 메서드를 사용하면 호출한 위치에서 블로킹되어 결과 값을 얻을 때까지 대기합니다.
//		*/
//	}
//}
