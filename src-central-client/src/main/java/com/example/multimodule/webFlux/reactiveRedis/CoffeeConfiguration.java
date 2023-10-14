//package com.example.springbreaking.reactiveRedis;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisOperations;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//public class CoffeeConfiguration {
//  /**
//   * ReactiveRedisOperations - Redis의 비동기 작업을 위한 인터페이스
//   * 스프링부트에 의해서 자동으로 주입된 ReactiveRedisConnectionFactory를 이용
//   * Jackson2JsonRedisSerializer를 이용해서 Redis에 객체가 저장될때는 json형태로 저장됩니다.
//   * RedisSerializationContext : 직렬화 전략을 정의
//   * ReactiveRedisTemplate : 인터페이스의 구현체로 Redis에 저장, 검색, 삭제하는 비동기 작업을 수행합니다.
//   * @param factory
//   * @return
//   */
//  @Bean
//  ReactiveRedisOperations<String, Coffee> redisOperations(ReactiveRedisConnectionFactory factory) {
//    Jackson2JsonRedisSerializer<Coffee> serializer = new Jackson2JsonRedisSerializer<>(Coffee.class);
//
//    RedisSerializationContext.RedisSerializationContextBuilder<String, Coffee> builder =
//        RedisSerializationContext.newSerializationContext(new StringRedisSerializer());
//
//    RedisSerializationContext<String, Coffee> context = builder.value(serializer).build();
//
//    return new ReactiveRedisTemplate<>(factory, context);
//  }
//
//}