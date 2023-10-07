package com.example.springbreaking.servlet.testingWeb;

import org.springframework.stereotype.Service;

@Service
public class TestGreetingService {
	public String greet() {
		return "Hello, World";
	}
}