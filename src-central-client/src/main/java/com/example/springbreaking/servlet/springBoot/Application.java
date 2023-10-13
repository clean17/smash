//package com.example.springbreaking.springBoot;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Arrays;
//
//@SpringBootApplication
//public class Application {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Application.class, args);
//	}
//
//	/**
//	 * ApplicationContext - 스프링 런타임 환경으로 모든 빈의 정보와 설정을 가지고 있습니다.
//	 * getBeanDefinitionNames - 모든 정의된 빈들의 이름 배열을 가져옵니다.
//	 * @param ctx
//	 * @return
//	 */
//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				System.out.println(beanName); // 모든 빈들을 콘솔에 출력
//			}
//
//		};
//	}
//
//}