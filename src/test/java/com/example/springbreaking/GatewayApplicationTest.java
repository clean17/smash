package com.example.springbreaking;

import com.example.springbreaking.gateway.GatewayApplication;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

/**
 * properties = {"httpbin=http://localhost:${wiremock.server.port}"}: httpbin이라는 프로퍼티에 WireMock 서버의 URL을 설정합니다.
 * WebTestClient webClient: 웹 애플리케이션에 대한 요청을 생성하고 응답을 검증하기 위한 Spring WebFlux의 비동기 클라이언트입니다.
 *
 * Stubs: WireMock을 사용하여 두 개의 스텁(stub)을 설정합니다. 이 스텁들은 테스트 중에 외부 서비스를 모킹하기 위해 사용됩니다.
 * 첫 번째 스텁은 /get 요청에 대한 응답으로 JSON 응답을 반환합니다.
 * 두 번째 스텁은 /delay/3 요청에 3초의 지연 후 no fallback 응답을 반환합니다.
 *
 * 첫 번째 요청은 /get 엔드포인트에 대한 것이며, 응답의 Hello 헤더가 "World"인지 검증합니다.
 * 두 번째 요청은 /delay/3 엔드포인트에 대한 것이며, Host 헤더를 포함하고 있습니다. 응답 본문이 "fallback"인지 검증합니다. 3초의 지연이 있기 때문에 서킷 브레이커의 fallback 메커니즘이 작동해야 합니다.
 */
@SpringBootTest(classes = GatewayApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"httpbin=http://localhost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
public class GatewayApplicationTest {

  @Autowired
  private WebTestClient webClient;

  @Test
  public void contextLoads() throws Exception {
    //Stubs
    stubFor(get(urlEqualTo("/get"))
        .willReturn(aResponse()
          .withBody("{\"headers\":{\"Hello\":\"World\"}}")
          .withHeader("Content-Type", "application/json")));
    stubFor(get(urlEqualTo("/delay/3"))
      .willReturn(aResponse()
        .withBody("no fallback")
        .withFixedDelay(3000)));

    webClient
      .get().uri("/get")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .jsonPath("$.headers.Hello").isEqualTo("World");

    webClient
      .get().uri("/delay/3")
      .header("Host", "www.circuitbreaker.com")
      .exchange()
      .expectStatus().isOk()
      .expectBody()
      .consumeWith(
        response -> assertThat(response.getResponseBody()).isEqualTo("fallback".getBytes()));
  }
}