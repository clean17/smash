//package com.example.springbreaking.validatingData;
//
//
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.validation.Valid;
//
//
///**
// * WebMvcConfigurer - SpringMVC 구성 설정
// *
// * addResourceHandlers - 정적 리소스 처리
// * addViewControllers - url을 뷰에 매핑
// * configurePathMatch  - 경로 매칭
// * addCorsMappings - CORS 설정
// * addInterceptors - 인터셉터 설정
// * configureMessageConverters - 메세지 컨버터 설정
// * configureViewResolvers - 뷰 리졸버 설정
// * configureDefaultServletHandling - 기본 서블릿 설정
// * getValidator - validator 설정
// * addFormatters - 사용자 정의 fommatter 설정
// *
// */
////@Controller
//
//	// 이녀석이 뷰리졸버를 훔쳐감
//public class WebController implements WebMvcConfigurer {
//
//	/**
//	 * ViewControllerRegistry - 뷰 컨트롤러를 등록 -> url 매핑
//	 * 로직없이 단순히 뷰를 매핑시켜준다.
//	 *
//	 * 단순 매핑, 리다이렉트, 상태코드 설정이 가능
//	 * @param registry
//	 */
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/results").setViewName("results"); // 리다이렉트
//	}
//
//	@GetMapping("/form")
//	public String showForm(PersonForm personForm) {
//		// webmvc 설정을 하지 않으면 디폴트 값은 /resources/templates/ 의 html 로 연결
//		return "form";
//	}
//
//	/**
//	 * Valid 체크의 간단한 방법으로 BindingResult에 에러를 담는다.
//	 * 주로 AOP를 통해서 발생한 에러를 Advice로 처리할 수 있다.
//	 * @param personForm
//	 * @param bindingResult
//	 * @return
//	 */
//	@PostMapping("/")
//	public String checkPersonInfo(@Valid PersonForm personForm, BindingResult bindingResult) {
//
//		// 직접 메소드에서 에러를 처리할 경우
//		if (bindingResult.hasErrors()) {
//			return "form";
//		}
//
//		return "redirect:/results";
//	}
//}