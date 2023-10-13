package com.example.springbreaking.servlet.testingWeb;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestGreetingController {

	private final TestGreetingService service;

	public TestGreetingController(TestGreetingService service) {
		this.service = service;
	}

	@RequestMapping(value = "/testingWeb", produces = MediaType.APPLICATION_JSON_VALUE) // 테스트할 때 헤더를 지정하고 싶다면
	public @ResponseBody String greeting() {
		return service.greet();
	}

}