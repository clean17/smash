package com.example.springbreaking.accessingDataJpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Jpa 레파지토리<객체, ID타입>
 * Jpa 메소드는 명명규칙을 지켜야 한다.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  Optional<List<Customer>> findByLastName(String lastName);

  Optional<Customer> findById(long id);
}