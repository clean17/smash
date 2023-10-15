//package com.example.multimodule.eurekaClient;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 유레카 클라이언트
// */
//@EnableEurekaClient
//@SpringBootApplication
//public class ServiceRegistrationAndDiscoveryClientApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ServiceRegistrationAndDiscoveryClientApplication.class, args);
//	}
//}
//
//@RestController
//class ServiceInstanceRestController {
//
//	@Autowired
//	private DiscoveryClient discoveryClient;
//
//	/**
//	 * 유레카 서버에 애플리케이션을 등록
//	 * @param applicationName
//	 * @return
//	 */
//	@RequestMapping("/service-instances/{applicationName}")
//	public List<ServiceInstance> serviceInstancesByApplicationName(
//			@PathVariable String applicationName) {
//		return this.discoveryClient.getInstances(applicationName);
//	}
//}