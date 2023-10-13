//package com.example.springbreaking.r2dbc;
//
//import org.springframework.data.r2dbc.repository.Query;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import reactor.core.publisher.Flux;
//
///**
// * Spring Data 패키지의 reactive 패키지
// * CRUD기능을 비동기 및 논블로킹 방식으로 수행하도록 지원하는 인터페이스
// */
//public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long> {
//    /**
//     * r2dbc의 @Query
//     * 해당 메소드의 쿼리를 직접 구성
//     *
//     * Flux<> : Project Reactor의 리액티브 타입 중 하나로 제네릭 타입의 여러 아이템을 순차적 + 비동기적으로 표현
//     * Project Reactor는 Spring 5 및 Spring WebFlux와 함께 도입된 리액티브 프로그래밍 라이브러리입니다.
//     * Reactor는 주로 두 가지 핵심 리액티브 타입, Mono와 Flux,로 구성됩니다.
//     *
//     * Flux<T>: 0개, 1개 또는 그 이상의 아이템을 순차적으로 표현하는 리액티브 스트림입니다.
//     * Mono<T>: 0개 또는 1개의 아이템을 표현하는 리액티브 스트림입니다.
//     *
//     * @param lastName
//     * @return
//     */
//    @Query("SELECT * FROM customer WHERE last_name = :lastname")
//    Flux<Customer> findByLastName(String lastName);
//
//}