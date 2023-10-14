package com.example.multimodule;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.multimodule.servlet.testingWeb.TestGreetingController;
import com.example.multimodule.servlet.testingWeb.TestGreetingService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @WebMvcTest : 스프링 부트 테스트 프레임워크에서 제공하는 웹 계층만을 테스트하기 위한 어노테이션입니다.
 * MockMvc 인스턴스를 자동으로 제공하여 실제 서버를 실행시키지 않고 MVC프레임워크 동작을 Mock할 수 있습니다.
 * 그러므로 RANDOM_PORT를 설정할 필요가 없습니다.
 * @WebMvcTest를 사용할 때는 컨트롤러를 주입합니다.
 */
@WebMvcTest(TestGreetingController.class)
public class WebMockTest {

	/**
	 * Mockito를 이용한 Mock테스트
	 */
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TestGreetingService service;


	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		when(service.greet()).thenReturn("Hello, Mock");
		mockMvc.perform(get("/testingWeb"))
				.andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				//.andExpect(content().contentType(MediaType.TEXT_PLAIN))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));
	}
}