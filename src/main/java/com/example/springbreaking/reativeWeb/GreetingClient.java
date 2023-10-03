package com.example.springbreaking.reativeWeb;

import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * 웹클라이언트
 *
 * WebFlux는 동기적 요청인 RestTemplate와 어울리지 않습니다.
 * 반응형 애플리케이션은 비동기적인 클래스를 제공합니다. - WebClient
 */
@Component
public class GreetingClient {

	private final WebClient client;

	// Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
	// We can use it to create a dedicated `WebClient` for our component.
	// WebClient.Builder를 사용하여 WebClient를 만들수 있습니다.
	// 생성자
	public GreetingClient(WebClient.Builder builder) {
		this.client = builder.baseUrl("http://localhost:8080").build();
	}

	/**
	 * retrieve() : 실제로 HTTP 요청을 보내고 응답을 받음
	 * bodyToMono(GreetingFlux.class) : 응답 본문을 GreetingFlux 클래스의 인스턴스로 변환합니다. ( 역직렬화 )
	 * @return
	 */
	public Mono<String> getMessage() {
		return this.client.get().uri("/helloFlux").accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(GreetingFlux.class)
				.map(GreetingFlux::getMessage);
	}

}
