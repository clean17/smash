package com.example.multimodule.other;

import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 어노테이션 없이 비동기
 */
@Controller
@EnableAsync
public class AsyncController {

    private static final ExecutorService executor = Executors.newFixedThreadPool(2);


    // 방법 1 executor - 주기적 실행
    public void scheduleMethod() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            // 실행하려는 코드
        }, 1, TimeUnit.SECONDS);
        executor.shutdown();
    }

    /////////////////////////////////////////////////////////////////////

    public String someMethod2() {
        asyncMethod();  // 비동기로 메서드 실행
        return "Some Result";  // 결과 반환
    }

    private void asyncMethod2() {
        executor.submit(() -> {
            // 비동기로 실행할 로직
            System.out.println("Executing async method");
        });
    }

    //////////////////////////////////////////////////////////////////////

    // 방법 2 CompletableFuture
    public String someMethod() {
        CompletableFuture.runAsync(this::asyncMethod);  // 비동기로 메서드 실행
        return "Some Result";  // 결과 반환
    }

    private void asyncMethod() {
        // 비동기로 실행할 로직
        System.out.println("Executing async method");
    }

    //////////////////////// CompletableFuture + ExecutorService
//    ExecutorService executor = Executors.newFixedThreadPool(17);
//    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
//        strList.parallelStream().forEach((item) -> {
//            List<String> tempList = new ArrayList<>();
//            int tot = 0;
//            try (BufferedReader br = new BufferedReader(new FileReader(item))) {
//                String line;
//                while ((line = br.readLine()) != null) {
//                    tempList.add(line);
//                    if(tempList.size() == 1000) {
//                        tot += tempList.size();
//                        tempList.clear();
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            synchronized (System.out) {
//                System.out.println("File: " + item + ", Line Count: " + tot);
//            }
//        });
//    }, executor).thenAccept(future ->{
//        // 비동기 끝나면 실행
//        try {
//            // 비동기 작업이 완료될 때까지 대기
//            future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    });

}
