//package com.example.springbreaking.webFlux.gateway;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//
///**
// * _@EnableConfigurationProperties는 @ConfigurationProperties로 등록된 클래스를 빈으로 등록하기 위해 사용합니다.
// */
//@SpringBootApplication
//@EnableConfigurationProperties(UriConfiguration.class)
//@RestController
//public class GatewayApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(GatewayApplication.class, args);
//    }
//
//
//    /**
//     * RouteLocatorBuilder - 빌더패턴으로 RouteLocator 생성
//     * @param builder
//     * @param uriConfiguration
//     * @return
//     */
///*    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(p -> p
//                        .path("/get") // 엔드포인트 라우팅 설정
//                        .filters(f -> f.addRequestHeader("Hello", "World")) // 필터 추가
//                        .uri("http://httpbin.org:80")) // 요청을 전달할 대상 서비스의 URI를 지정
//                .build();
//    }*/
//// 아래의 요청을 보내면 해당 응답을 받습니다.
////    $ curl http://localhost:8080/get
////    {
////        "args": {},
////        "headers": {
////        "Accept": "*/*",
////                "Content-Length": "0",
////                "Forwarded": "proto=http;host=\"localhost:8080\";for=\"127.0.0.1:10736\"",
////                "Hello": "World", // 라우팅으로 들어간 헤더
////                "Host": "httpbin.org",
////                "User-Agent": "curl/7.87.0",
////                "X-Amzn-Trace-Id": "Root=1-6521681a-3412e8c81e4f9d8d2db750cf",
////                "X-Forwarded-Host": "localhost:8080"
////    },
////        "origin": "127.0.0.1, 211.54.71.169",
////            "url": "http://localhost:8080/get"
////    }
//
//
//    /**
//     * RouteLocatorBuilder에 더해 UriConfiguration빈을 가져와 RouteLocator 생성
//     * 위 메소드와 중요한 차이는 경로 라우팅이 아닌 호스트 라우팅을 사용 한다는 것
//     * circuitbreaker.com -> HTTPBin으로 라우팅 -> 회로 차단기에 래핑하는 필터를 등록
//     * @param builder
//     * @param uriConfiguration
//     * @return
//     */
//    @Bean
//    public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
//        String httpUri = uriConfiguration.getHttpbin();
//        return builder.routes()
//                .route(p -> p
//                        .path("/get")
//                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri(httpUri))
//                .route(p -> p
//                        .host("*.circuitbreaker.com")
//                        .filters(f -> f
//                                .circuitBreaker(config -> config
//                                        .setName("mycmd") // 회로차단기 이름
//                                        .setFallbackUri("forward:/fallback") // 우회URI 를 입력하지 않으면 HTTP/1.1 504 Gateway Timeout 발생
//                                ))
//                        .uri(httpUri))
//                .build();
//    }
//    // $ curl --dump-header - --header 'Host: www.circuitbreaker.com' http://localhost:8080/delay/3
////    Content-Type: text/plain;charset=UTF-8
////    Content-Length: 8
////    Date: Sat, 07 Oct 2023 14:33:02 GMT
////
////    fallback
//
//
//    @RequestMapping("/fallback")
//    public Mono<String> fallback() {
//        return Mono.just("fallback");
//    }
//}
//
//@ConfigurationProperties
//class UriConfiguration {
//
//    private String httpbin = "http://httpbin.org:80";
//
//    public String getHttpbin() {
//        return httpbin;
//    }
//
//    public void setHttpbin(String httpbin) {
//        this.httpbin = httpbin;
//    }
//}
