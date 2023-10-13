package com.example.springbreaking.servlet.cors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingCorsController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();

	/**
	 * CORS 허용 주소 origins = "http://localhost:8080"
	 * 허용할 메소드 methods
	 * 허용할 헤더 allowedHeaders
	 * 브라우저에 노출시킬 헤더 exposedHeaders
	 * 인증 정보가 필요한지 allowCredentials (default : false)
	 * 최대 캐시 시간 maxAge
	 *
	 *
	 * 하지만 일반적으로는 WebMvcConfigurer 를 구현해서 전역 설정을 세팅한다.
	 *
	 * @param name
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/greetingAngular")
	public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		System.out.println("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
		// 리턴 json {"id":1,"content":"Hello, World!"}

		/**
		 * 추가적으로 angular 연습용으로 helloAngular.js 가 이 주소로 요청을 보내고 json을 가져간다.
		 */
	}

}