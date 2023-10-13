package com.example.springbreaking.servlet.springBoot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping("/")
	@ResponseBody
	public String index() {
		return "Greetings from Spring Boot!";
	}

	/**
	 * @ModelAttribute 는 파라미터에 사용해야 합니다
	 * 메소드에 사용한다면 temp.jsp를 응답하지 않고 model.jsp를 응답하므로 404 에러가 나옵니다.
	 * 왜냐하면 파라미터 수준이 아닌 메소드 수준에서는 모델에 넣은 데이터가 메소드의 리턴값이므로
	 * 모델에 리턴값을 넣을때 메소드의 리턴값을 가져갑니다.
	 * 따라서 메소드의 반환값이 의도와 달라져 뷰 리졸버가 없는 데이터를 반환합니다.
	 * @return
	 */
	@GetMapping(value = "/model")
	@ModelAttribute("message")
	public String message() {
		return "temp";
	}

	/**
	 * 아래와 같이 파라미터에 사용한다면 데이터를 자동으로 객체에 매핑한뒤 모델에 넣어 반환합니다.
	 * @param emptyObject
	 * @return
	 */
	@GetMapping(value = "/model2")
	public String message2(@ModelAttribute("message") EmptyObject emptyObject) {
		return "temp";
	}
}