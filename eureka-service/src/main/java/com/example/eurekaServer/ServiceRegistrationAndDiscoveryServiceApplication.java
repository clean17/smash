package com.example.eurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 레지스트리가 연결할 복제본 노드가 필요.. 프로덕션 환경에서는 둘 이상의 레지스트리 인스턴스가 필요
 * 연습 환경에서는 관련 로깅을 비활성화
 */
@EnableEurekaServer
@SpringBootApplication
public class ServiceRegistrationAndDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistrationAndDiscoveryServiceApplication.class, args);
	}
}