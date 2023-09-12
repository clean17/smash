package com.example.springbreaking.accessingDataJpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Jpa 레파지토리<객체, ID타입>
 * Jpa 메소드는 명명규칙을 지켜야 한다.
 *
 * CrudRepository가 기본 제공하는 메소드
 * save(S entity), saveAll(Iterable<S> entities)
 * findById(ID id), existsById(ID id)
 * findAll(), findAllById(Iterable<ID> ids)
 * count(),
 * deleteById(ID id), delete(S entity),
 * deleteAll((Iterable<? extends T> entities)), deleteAll()
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  // 하나의 엔티티만 반환할 경우 Optional을 사용한다.
  List<Customer> findByLastName(String lastName);

//  Optional<Customer> findById(long id); CrudRepository는 이미 findById를 제공한다.
}