//package com.example.springbreaking.cassandra;
//
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.UUID;
//
///**
// * Spring Data의 구현체는 애플리케이션 실행시 ApplicationContext에 빈으로 자동 등록
// */
//public interface VetRepository extends CrudRepository<Vet, UUID> {
//  Vet findByFirstName(String username);
//}