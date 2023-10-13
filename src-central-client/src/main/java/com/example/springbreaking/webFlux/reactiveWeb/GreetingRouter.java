//package com.example.springbreaking.reactiveWeb;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
///**
// * 라우터
// *
// * proxyBeanMethods = false : @Configuration 클래스 최적화를 위한 설정
// * 기본적으로 Configuration 클래스 내의 Bean 메소드는 프록시가 되는데 이 프록시가 빈 재사용을 보장한다.
// */
//@Configuration(proxyBeanMethods = false)
//public class GreetingRouter {
//
//	/**
//	 * WebFlux의 함수형 스타일 라우팅을 정의하는 인터페이스
//	 * 요청에 대한 처리 로직을 정의하는 HandlerFunction과 연결 - GreetingHandler ( 요청과 핸들러를 매핑 )
//	 * @param greetingHandler
//	 * @return
//	 */
//	@Bean
//	public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
//
//		// 요청 주소와 헤더만 허용
//		// 조건이 맞으면 greetingHandler의 hello메소드 호출
//		return RouterFunctions
//				.route(RequestPredicates.GET("/helloflux").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), greetingHandler::helloflux);
//	}
//}
