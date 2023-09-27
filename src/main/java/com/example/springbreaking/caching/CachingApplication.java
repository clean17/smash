//package com.example.springbreaking.caching;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cache.annotation.EnableCaching;
//
///**
// * @EnableCaching -> Spring Bean의 공용 메소드에서 @Cacheable을 찾아 사후 프로세스를 트리거
// *   - 메소드 실행결과를 캐시에 저장함, 지금은 특정 캐싱 라이브러리를 사용하지 않으므로 ConcurrentHashMap에 저장됨
// * 메소드 호출을 가로채고 캐싱 동작을 처리하기 위한 프록시가 생성됨
// *
// * 캐싱된 데이터는 내부 메모리에 바이트 형태로 저장되므로 캐싱 데이터에 접근하려면 역직렬화 과정을 거쳐야한다.
// * 그러므로 객체를 캐싱할 때 Serializable를 구현시킨다.
// * 라이브러리를 사용하지 않으면 ConcurrentMapCacheManager를 이용해서 JVM 메모리에 캐시를 구현한다.
// */
//@SpringBootApplication
//@EnableCaching
//public class CachingApplication {
//
//  public static void main(String[] args) {
//    SpringApplication.run(CachingApplication.class, args);
//  }
//
//}