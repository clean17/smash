package com.example.multimodule.webFlux.reactiveWeb;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GreetingFlux {

	private String message;

	@Override
	public String toString() {
		return "Greeting{" +
				"message='" + message + '\'' +
				'}';
	}
}
