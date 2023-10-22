package com.example.springbreaking.servlet.asyncMethod;

import com.example.multimodule.servlet.asyncMethod.GitHubLookupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class AsyncAppRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(AsyncAppRunner.class);

  private final GitHubLookupService gitHubLookupService;

  public AsyncAppRunner(GitHubLookupService gitHubLookupService) {
    this.gitHubLookupService = gitHubLookupService;
  }

  /**
   * CompletableFuture <- 비동기 결과 반환 .. Promise와 유사
   *
   * 비동기가 완료되면 실행시킬 코드는 아래처럼 만든다. ( + thenApply, thenAccept, thenRun )
   * future.thenApply(result -> result + " from CompletableFuture!");
   *
   * 비동기 작업은 join 메소드를 이용해서 기다린다. ( join은 멀티스레드에서 기본적으로 사용 )
   *
   * 예외가 발생하면 아래처럼 처리 ( + handle, exceptionally, whenComplete )
   * future.exceptionally(ex -> "An error occurred: " + ex.getMessage());
   *
   * 비동기가 완료되면 실행시킬 -> complete, completeExceptionally
   * 비동기 연속 실행 -> thenApplyAsync, thenAcceptAsync
   * @param args incoming main method arguments
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    // Start the clock
    long start = System.currentTimeMillis();

    // 서비스의 요청마다 새로운 비동기 스레드 생성
    // Kick of multiple, asynchronous lookups
    CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
    CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
    CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");

    // 비동기 작업들이 모두 완료될 때까지 대기 -> allOf,  anyOf
    // Wait until they are all done
    CompletableFuture.allOf(page1,page2,page3).join();

    // Print results, including elapsed time
    logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
    logger.info("--> " + page1.get());
    logger.info("--> " + page2.get());
    logger.info("--> " + page3.get());

  }

}