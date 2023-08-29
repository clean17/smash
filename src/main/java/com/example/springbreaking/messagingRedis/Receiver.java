package com.example.springbreaking.messagingRedis;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Redis 메세지 수신기
 */
@Slf4j
public class Receiver {

    /**
     * Atomic - 원자적 - 스레드 안전, 동시 접근 불가
     * 여러 스레드가 동시에 카운터 값을 증가 시킬 경우에 사용
     */
    private AtomicInteger counter = new AtomicInteger();

    // MessageListenerAdapter가 이 메소드를 사용해서 메시지를 수신
    public void receiveMessage(String message) {
        log.info("Received <" + message + ">");
        counter.incrementAndGet();
    }

    public int getCount() {
        return counter.get();
    }
}