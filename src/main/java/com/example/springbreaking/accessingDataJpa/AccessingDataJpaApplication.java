//package com.example.springbreaking.accessingDataJpa;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.util.Optional;
//
//@SpringBootApplication
//@Slf4j
//public class AccessingDataJpaApplication {
//
//  public static void main(String[] args) {
//    SpringApplication.run(AccessingDataJpaApplication.class);
//  }
//
//  /**
//   * @param repository
//   * @return
//   */
//  @Bean
//  public CommandLineRunner demo(CustomerRepository repository) {
//    return (args) -> {
//      // save a few customers
//      repository.save(new Customer("Jack", "Bauer"));
//      repository.save(new Customer("Chloe", "O'Brian"));
//      repository.save(new Customer("Kim", "Bauer"));
//      repository.save(new Customer("David", "Palmer"));
//      repository.save(new Customer("Michelle", "Dessler"));
//
//      // fetch all customers
//      log.info("Customers found with findAll():");
//      log.info("-------------------------------");
//      for (Customer customer : repository.findAll()) {
//        log.info(customer.toString());
//      }
//      log.info("");
//
//      // fetch an individual customer by ID
//      Optional<Customer> customerOptional = repository.findById(1L);
//
//      // 존재할 경우 item변수명 변경
//      customerOptional.ifPresent(customer -> {
//        log.info("Customer found with findById(1L):");
//        log.info("--------------------------------");
//        log.info(customer.toString());
//        log.info("");
//      });
//
//
//
//      // fetch customers by last name
//      log.info("Customer found with findByLastName('Bauer'):");
//      log.info("--------------------------------------------");
//      repository.findByLastName("Bauer").forEach(bauer -> {
//        log.info(bauer.toString());
//      });
//       for (Customer bauer : repository.findByLastName("Bauer")) {
//        log.info(bauer.toString());
//       }
//      log.info("");
//
//
//       /*
//       * 실행결과
//       * Customers found with findAll():
//       : -------------------------------
//       : Customer[id=1, firstName='Jack', lastName='Bauer']
//       : Customer[id=2, firstName='Chloe', lastName='O'Brian']
//       : Customer[id=3, firstName='Kim', lastName='Bauer']
//       : Customer[id=4, firstName='David', lastName='Palmer']
//       : Customer[id=5, firstName='Michelle', lastName='Dessler']
//       :
//       : Customer found with findById(1L):
//       : --------------------------------
//       : Customer[id=1, firstName='Jack', lastName='Bauer']
//       :
//       : Customer found with findByLastName('Bauer'):
//       : --------------------------------------------
//       : Customer[id=1, firstName='Jack', lastName='Bauer']
//       : Customer[id=3, firstName='Kim', lastName='Bauer']
//       : Customer[id=1, firstName='Jack', lastName='Bauer']
//       : Customer[id=3, firstName='Kim', lastName='Bauer']
//       * */
//    };
//  }
//
//}