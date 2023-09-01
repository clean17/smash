package com.example.springbreaking.batchProcessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchProcessingApplication {

  /**
   * SpringApplication.exit - 스프링 애플리케이션 컨텍스트 종료
   *   배치 작업 종료 시
   * System.exit - JVM 종료
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    System.exit(SpringApplication.exit(SpringApplication.run(BatchProcessingApplication.class, args)));
  }
}