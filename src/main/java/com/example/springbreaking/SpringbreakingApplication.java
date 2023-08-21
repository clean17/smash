package com.example.springbreaking;

import com.example.springbreaking.uploadingfiles.storage.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
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
	private static final Logger log = LoggerFactory.getLogger(SpringbreakingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbreakingApplication.class, args);
	}

	/**
	 * Builder의 build 메소드를 호출해서 RestTemplate 반환
	 *
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

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
/*	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Quote quote = restTemplate.getForObject(
					"http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());
		};
	}*/

	// 모든 데이터 리셋
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
