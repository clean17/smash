//package com.example.springbreaking.cors;
//
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 또는 yml 에서 설정가능합니다.
// *
// * spring.mvc.cors.allowed-origins=http://example.com, http://another-example.com
// * spring.mvc.cors.allowed-methods=GET,POST,PUT,DELETE
// */
//
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Value("${cors.allowed-origins}")
//    private String[] allowedOrigins;
//
//    @Value("${cors.allowed-methods}")
//    private String[] allowedMethods;
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins(allowedOrigins)
//                .allowedMethods(allowedMethods)
//                .allowedHeaders("header1", "header2", "header3")
//                .exposedHeaders("header1", "header2")
//                .allowCredentials(true)
//                .maxAge(3600);
//    }
//}
