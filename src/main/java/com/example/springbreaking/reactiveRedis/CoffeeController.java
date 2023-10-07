//package com.example.springbreaking.reactiveRedis;
//
//import org.springframework.data.redis.core.ReactiveRedisOperations;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//
//@RestController
//public class CoffeeController {
//
//  // Redis 인터페이스
//  private final ReactiveRedisOperations<String, Coffee> coffeeOps;
//
//  CoffeeController(ReactiveRedisOperations<String, Coffee> coffeeOps) {
//    this.coffeeOps = coffeeOps;
//  }
//
//  @GetMapping("/coffees")
//  public Flux<Coffee> all() {
//    return coffeeOps.keys("*")
//        .flatMap(coffeeOps.opsForValue()::get);
//  }
//  /**
//   * 결과는
//   * // 20231006225102
//   * // http://localhost:8080/coffees
//   *
//   * [
//   *   {
//   *     "id": "5a090ba2-dbd1-4b4f-aecc-af57b8fbe45f",
//   *     "name": "Jet Black Redis"
//   *   },
//   *   {
//   *     "id": "ae61d3bc-ec1e-40de-bb09-af77a8689313",
//   *     "name": "Black Alert Redis"
//   *   },
//   *   {
//   *     "id": "b162f9b7-0a73-4e97-82bf-002cc30d589f",
//   *     "name": "Darth Redis"
//   *   }
//   * ]
//   */
//}