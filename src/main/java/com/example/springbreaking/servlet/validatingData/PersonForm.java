package com.example.springbreaking.servlet.validatingData;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * validation 체크
 * javax.validation.constraints 패키지를 필요로 한다.
 */
@Getter
@Setter
@ToString
public class PersonForm {

	@NotNull
	@Size(min=2, max=30)
	private String name;

	@NotNull
	@Min(18)
	private Integer age;

}