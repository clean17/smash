//package com.example.springbreaking.cors;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@SpringBootApplication
//public class RestServiceCorsApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(RestServiceCorsApplication.class, args);
//	}
//
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				/**
//				 * localhost에서만 허용된 엔드포인트 설정
//				 */
//				registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");
//			}
//		};
//	}
//}