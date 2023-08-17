## 프로젝트 생성
https://start.spring.io/ `Generate` 버튼으로 zip 파일 받는다.


## 기본 형태
```java
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
```
`http://localhost:8080/hello?name=merci` 으로 요청하면
`Hello merci!` 로 응답

## @SpringBootApplication

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters = { @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
public @interface SpringBootApplication { // ...
```
`@SpringBootConfiguration`, `@EnableAutoConfiguration`, `@ComponentScan` 로 구성되어 있다.

- `@SpringBootConfiguration`<br>
스프링의 기본 설정 클래스를 구성하고 `@configuration`으로 설정된 `bean`을 등록한다.<br>
-> 스프링 MVC 및 IoC 컨테이너 등록

- `@EnableAutoConfiguration`는 스프링부트가 클래스 경로 설정, 다양한 설정에 기반하여 `bean`을 자동으로 구성하도록 한다.
  ( `spring-context.xml`의 역할 )

- `@ComponentScan`은 현재 패키지 및 하위 패키지를 스캔하여 컴포넌트를 찾아서 `bean`으로 등록한다.

이러한 자동구성에서 제외시키고 싶을때는 아래의 방법을 이용한다.
```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
```

-- 잠시 멈춤 --
