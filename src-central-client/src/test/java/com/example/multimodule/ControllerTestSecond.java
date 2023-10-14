package com.example.multimodule;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 웹 요청을 테스트하는 두번째 방법
 *
 * RANDOM_PORT 사용
 * 충돌을 방지, 여러 테스트를 동시에 진행, 테스트의 독립성을 확보
 * RANDOM_PORT를 사용하면  TestRestTemplate 또는 WebTestClient 도구를 이용해 HTTP 호출을 테스트할 수 있습니다.
 */
@SpringBootTest(classes = SpringbreakingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTestSecond {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!");
    }
}