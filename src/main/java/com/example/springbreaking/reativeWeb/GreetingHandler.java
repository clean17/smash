package com.example.springbreaking.reativeWeb;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

/**
 * WebFlux 처리기
 */
@Component
public class GreetingHandler {

	/**
	 * 항상 Hello Spring이 포함된 객체를 json으로 반환 + 비동기적인 웹 응답을 처리
	 *
	 * Spring WebFlux는 Spring의 반응형 프로그래밍 모델로, 트래픽이 많은 시스템에서 확장성을 제공하기 위해 설계되었습니다.
	 * Mono : 0 or 1 개의 결과를 포함하는 반응형 스트림
	 * ServerRequest : HTTP 요청에 대한 정보를 담고 있는 객체
	 * BodyInserters.fromValue() : 주어진 객체를 HTTP 응답 본문으로 사용하기 위해 필요한 BodyInserter를 생성
	 *
	 * BodyInserters : Spring WebFlux에서 제공하는 유틸리티 클래스로
	 * WebClient나 서버 측 ServerResponse를 사용하여 HTTP 응답 혹은 요청 본문(body)을 채우는데 사용됩니다.
	 *
	 * BodyInserters에는 다양한 정적 메서드가 포함
	 * fromValue(Object): 주어진 객체를 HTTP 본문으로 사용합니다.
	 * fromPublisher(Publisher<?>, Class<?>): Reactor의 Mono나 Flux와 같은 Publisher를 HTTP 본문으로 사용합니다.
	 * fromFormData(String, String): Form 데이터를 HTTP 본문으로 사용합니다.
	 * @param request
	 * @return
	 */
	public Mono<ServerResponse> hello(ServerRequest request) {
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromValue(new GreetingFlux("Hello, Spring!")));
	}
}
