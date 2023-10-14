//package com.example.multimodule;
//
//import org.junit.jupiter.api.Test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(classes = SpringbreakingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
//public class TestingWebApplicationTests {
//
//	/**
//	 * 테스트 환경에서 RANDOM_PORT or DEFINED_PORT가 설정되어 있지 않다면 local.server.port 속성은 존재하지 않는다.
//	 */
//	@Value(value="${local.server.port}")
//	private int port;
//
//	// TestRestTemplate도 RANDOM_PORT가 필요
//	@Autowired
//	private TestRestTemplate restTemplate;
//
//	/**
//	 * MockMvc를 주입받기 위해서는 @AutoConfigureMockMvc가 필요합니다.
//	 */
//	@Autowired
//	private MockMvc mockMvc;
//
//
//	@Test
//	public void greetingShouldReturnDefaultMessage() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
//				String.class)).contains("Greetings from Spring Boot!");
//	}
//
//	@Test
//	public void shouldReturnDefaultMessage() throws Exception {
//		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Greetings from Spring Boot!")));
//	}
//}