//package com.example.springbreaking.r2dbc;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.time.Duration;
//import java.util.Arrays;
//
//@SpringBootApplication
//public class AccessingDataR2dbcApplication {
//
//    private static final Logger log = LoggerFactory.getLogger(AccessingDataR2dbcApplication.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(AccessingDataR2dbcApplication.class, args);
//    }
//
//    /**
//     * 몇번 겪어보는 문제가 있는데 Spring Data의 Crud인터페이스를 구현한 인터페이스는 애플리케이션 실행시 자동으로 빈에 등록되는것으로 알고 있음
//     * 애플리케이션 실행시 CommandLineRunner에서 해당 빈을 파라미터로 주입시 가끔 빈을 찾을수 없다는 에러가 나옴
//     * 이유가 무엇인지 규명해야함
//     *
//     * 빈을 찾기 위해 콘솔에 빈추가 설정을 넣음
//     *
//     * 찾아보니까 Repository로 등록되지 못함. @Table 을 넣으라고 함
//     * import org.springframework.data.relational.core.mapping.Table;
//     *
//     * @param repository
//     * @return
//     */
//    @Bean
//    public CommandLineRunner demo(CustomerRepository repository) {
//
//        return (args) -> {
//            // save a few customers
//            repository.saveAll(Arrays.asList(new Customer("Jack", "Bauer"),
//                new Customer("Chloe", "O'Brian"),
//                new Customer("Kim", "Bauer"),
//                new Customer("David", "Palmer"),
//                new Customer("Michelle", "Dessler")))
//                .blockLast(Duration.ofSeconds(10));
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            repository.findAll().doOnNext(customer -> {
//                log.info(customer.toString());
//            }).blockLast(Duration.ofSeconds(10));
//
//            log.info("");
//
//            // fetch an individual customer by ID
//			repository.findById(1L).doOnNext(customer -> {
//				log.info("Customer found with findById(1L):");
//				log.info("--------------------------------");
//				log.info(customer.toString());
//				log.info("");
//			}).block(Duration.ofSeconds(10));
//
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByLastName("Bauer").doOnNext(bauer -> {
//                log.info(bauer.toString());
//            }).blockLast(Duration.ofSeconds(10));;
//            log.info("");
//        };
//    }
//
//}