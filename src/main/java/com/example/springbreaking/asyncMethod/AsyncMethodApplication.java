package com.example.springbreaking.asyncMethod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 비동기 애플리케이션을 만들기 위해서 @EnableAsync 사용한다.
 * @Async가 달린 메소드는 자동으로 호출된다.
 * @Async가 달린 메소드는 호출될 때마다 새로운 스레드에서 실행된다.
 *
 * 예를들어 아래와 같이 만든다면
 *
 * @Configuration
 * @EnableAsync
 * public class AppConfig implements AsyncConfigurer {
 *
 *     @Override
 *     public Executor getAsyncExecutor() {
 *         ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
 *         executor.setCorePoolSize(5);
 *         executor.setMaxPoolSize(10);
 *         executor.setQueueCapacity(25);
 *         executor.initialize();
 *         return executor;
 *     }
 * }
 *
 * 비동기 작업에 사용될 스레드풀의 세부사항을 설정
 *
 * AsyncConfigurer 인터페이스의 getAsyncUncaughtExceptionHandler를 오버라이딩해서 예외 처리
 */
@SpringBootApplication
@EnableAsync
public class AsyncMethodApplication {

  /**
   * SpringApplication.run()은 ApplicationContext를 반환한다.
   * 비동기 작업이 완료되면 close된다.
   *
   * @EnableAsync설정 -> @Async 호출시마다 비동기 스레드에서 실행
   * 애플리케이션 실행 시 CommandLineRunner가 실행
   * -> CompletableFuture를 반환한는 비동기 메소드(@Async) 호출
   * @param args
   */
  public static void main(String[] args) {
    // close the application context to shut down the custom ExecutorService
    SpringApplication.run(AsyncMethodApplication.class, args).close();
  }

  /**
   * 상단 주석처럼 만드는 방법 또는 아래처럼 만들어서 Executor를 반환하는 빈을 설정
   * @return
   */
  @Bean
  public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(2);
    executor.setMaxPoolSize(2);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("GithubLookup-");
    executor.initialize();
    return executor;
  }


}