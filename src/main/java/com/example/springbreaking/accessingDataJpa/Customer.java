//package com.example.springbreaking.accessingDataJpa;
//
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//
//
////import org.hibernate.annotations.Entity; 사용하지 않도록 주의
////import org.springframework.data.annotation.Id;
//
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//
///**
// * Jpa 엔티티
// * Id 명시
// * 기본생성자 필요
// *
// * JPA의 어노테이션 실수 !!! javax.persistence를 사용해야 함
// */
//@Entity
//@NoArgsConstructor
//@Getter
//public class Customer {
//
//  /**
//   * 자동 증가
//   */
//  @Id
//  @GeneratedValue(strategy= GenerationType.AUTO)
//  private Long id;
//  private String firstName;
//  private String lastName;
//
//  public Customer(String firstName, String lastName) {
//    this.firstName = firstName;
//    this.lastName = lastName;
//  }
//
//  @Override
//  public String toString() {
//    return String.format(
//        "Customer[id=%d, firstName='%s', lastName='%s']",
//        id, firstName, lastName);
//  }
//}