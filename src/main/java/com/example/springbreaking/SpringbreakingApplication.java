package com.example.springbreaking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringbreakingApplication {


	/**
	 * slf4j 라이브러리와 함께 사용되는 코드 조각
	 *
	 * log.debug("Debug message");
	 * log.info("Informational message");
	 * log.warn("Warning message");
	 * log.error("Error message");
	 *
	 * 아래 코드로 생성하지 않는다면 Lombok의 @Slf4j 어노테이션으로 생성할 수 있다.
	 */
//	private static final Logger log = LoggerFactory.getLogger(SpringbreakingApplication.class);

	public static void main(String[] args) throws InterruptedException {

		// Redis 메세징, 진입점 클래스를 명시
		ApplicationContext ctx = SpringApplication.run(SpringbreakingApplication.class, args);

		// Redis 메세징
//		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
//		Receiver receiver = ctx.getBean(Receiver.class);
//
//		/**
//		 * Poling 메세지 리시버 활성화
//		 * 'chat' 토픽으로 전송된 메시지일 경우에만 카운트 증가
//		 */
//
//		while (receiver.getCount() == 0) {
//
//			log.info("Sending message...");
//			template.convertAndSend("chat", "Hello from Redis!");
//			Thread.sleep(500L);
//		}

		// 애플리케이션 강제 종료
//		System.exit(0);
	}


	/**
	 * Builder의 build 메소드를 호출해서 RestTemplate 반환
	 *
	 * @param builder
	 * @return
	 */

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return builder.build();
//	}


   /**
	 * 애플리케이션 시작시 자동 호출
	 *
	 * Rest 클라이언트 - Rest서버에 응답을 보내고 응답을 받도록 함
	 * Postman 이나 Insomnia는 GUI기반의 Rest 클라이언트라고 할 수 있다.
	 *
	 * 이러한 기능을 라이브러리를 이용할 수 있다.
	 * Java에서는 RestTemplate를 이용하는것이 대표적인 방법이다.
	 *
	 * @param restTemplate
	 * @return
	 * @throws Exception
	 */


	// RestTemplate
/*	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());
		};
	}*/



   /**
	 * 데이터셋 초기화 - uploadingfiles 패키지
	 * @param storageService
	 * @return
	 */

//	@Bean
//	CommandLineRunner init(StorageService storageService) {
//		return (args) -> {
//			storageService.deleteAll();
//			storageService.init();
//		};
//	}

}

