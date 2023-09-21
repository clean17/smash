package com.example.springbreaking.cors;


/**
 * 일반적인 전역 설정
 */
/*@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 엔드포인트에 대한 CORS 설정
                .allowedOrigins("http://example.com", "http://another-example.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Header1", "Header2", "Header3")
                .exposedHeaders("Header1", "Header2")
                .allowCredentials(true)
                .maxAge(3600);
    }
}*/



/**
 * 또는 yml 에서 설정가능합니다.
 *
 * spring.mvc.cors.allowed-origins=http://example.com, http://another-example.com
 * spring.mvc.cors.allowed-methods=GET,POST,PUT,DELETE
 */