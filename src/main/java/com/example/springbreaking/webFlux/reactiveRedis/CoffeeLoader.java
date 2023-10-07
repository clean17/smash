//package com.example.springbreaking.reactiveRedis;
//
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisOperations;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Flux;
//
//import javax.annotation.PostConstruct;
//import java.util.UUID;
//
//@Component
//public class CoffeeLoader {
//  private final ReactiveRedisConnectionFactory factory;
//  private final ReactiveRedisOperations<String, Coffee> coffeeOps;
//
//  public CoffeeLoader(ReactiveRedisConnectionFactory factory, ReactiveRedisOperations<String, Coffee> coffeeOps) {
//    this.factory = factory;
//    this.coffeeOps = coffeeOps;
//  }
//
//  /**
//   *  @PostConstruct
//   *  - 객체가 생성되고, 필요한 의존성이 주입된 후에 실행되어야 할 로직을 정의하는데 사용
//   *  - 한번만 인스턴스화되고 모든 의존성이 주입된 직후 한번만 실행
//   *  - 컴포넌트로 등록된 CoffeeLoader 빈이 완전히 등록된 후 메소드가 호출됨
//   *
//   *  flushAll()을 실행함으로써 Redis의 데이터를 모두 삭제 : 초기화
//   *  Flux.just로 Flux스트림을 생성
//   *  thenMany를 통해서 현재 스트림의 결과를 반환 즉 스트림을 위한 then 로직
//   *  flatMap은 또다른 리액티브 타입을 반환합니다. -> thenMany
//   *  순서가 보장되지 않습니다. 병렬적으로 흘러온 스트림을 병합해 반환하므로 순서가 중요한 경우 concatMap을 고려할 수 있습니다.
//   *  opsForValue를 통해 인터페이스의 (자기자신) 인스턴스를 반환
//   *  set을 통해 Redis에 객체를 저장합니다.
//   *  CoffeeConfiguration에서 직렬화 설정을 했으므로 json데이터가 저장됩니다.
//   *  keys("*")로 모든 키를 가져와 각 키의 모든 값을 가져오고 콘솔에 출력합니다.
//   *
//   *  결과는
//   *  Coffee(id=5a090ba2-dbd1-4b4f-aecc-af57b8fbe45f, name=Jet Black Redis)
//   *  Coffee(id=ae61d3bc-ec1e-40de-bb09-af77a8689313, name=Black Alert Redis)
//   *  Coffee(id=b162f9b7-0a73-4e97-82bf-002cc30d589f, name=Darth Redis)
//   */
//  @PostConstruct
//  public void loadData() {
//    factory.getReactiveConnection().serverCommands().flushAll().thenMany(
//        Flux.just("Jet Black Redis", "Darth Redis", "Black Alert Redis")
//            .map(name -> new Coffee(UUID.randomUUID().toString(), name))
//            .flatMap(coffee -> coffeeOps.opsForValue().set(coffee.getId(), coffee)))
//        .thenMany(coffeeOps.keys("*")
//            .flatMap(coffeeOps.opsForValue()::get))
//        .subscribe(System.out::println);
//  }
//}