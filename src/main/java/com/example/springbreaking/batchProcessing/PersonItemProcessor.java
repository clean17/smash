package com.example.springbreaking.batchProcessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

/**
 * 일관 처리의 패러다임 : 데이터 수집 -> 파이프
 * Spring Batch의 ItemProcessor 인터페이스를 구현
 * Spring Batch는 개발자가 많은 코드를 작성하지 않도록 유틸리티 클래스를 제공
 *
 */
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

  private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

  @Override
  public Person process(final Person person) throws Exception {
    final String firstName = person.getFirstName().toUpperCase();
    final String lastName = person.getLastName().toUpperCase();

    final Person transformedPerson = new Person(firstName, lastName);

    log.info("Converting (" + person + ") into (" + transformedPerson + ")");

    return transformedPerson;
  }

}