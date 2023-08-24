


<details>
  <summary>프로젝트 생성</summary>

## 프로젝트 생성
https://start.spring.io/ `Generate` 버튼으로 zip 파일 받습니다.

</details>
<details>
  <summary>스프링부트 기본형태</summary>

## 기본 형태
```java
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}
```
`http://localhost:8080/hello?name=merci` 으로 요청하면
`Hello merci!` 로 응답

<details>
  <summary>@SpringBootApplication</summary>

</details>


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
`@SpringBootConfiguration`, `@EnableAutoConfiguration`, `@ComponentScan` 로 구성되어 있습니다.

- `@SpringBootConfiguration`<br>
  스프링의 기본 설정 클래스를 구성하고 `@configuration`으로 설정된 `bean` 등록<br>
  -> 스프링 MVC 및 IoC 컨테이너 등록
  <br>


- `@EnableAutoConfiguration`<br>
  자동 구성을 활성화 -> 클래스 패스(+`build.gradle`)에 기반하여 `bean` 등록<br> `spring.factories` 파일을 기반으로 스프링부트의 자동 구성 `bean` 등록<br>

  (`spring-context.xml`의 역할)


- `@ComponentScan`은 현재 패키지 및 하위 패키지를 스캔하여 컴포넌트를 찾아서 `bean` 등록

이러한 자동구성에서 제외시키고 싶을때는 아래의 방법을 이용합니다.
```java
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
```

</details>

<details>
  <summary>CRUDRepository / JPARepository 차이</summary>

### CRUDRepository / JPARepository 차이

`CRUDRepository`는 Spring Data JPA 의 상위 인터페이스

`JPARepository`는 위 인터페이스에 추가로 JPA메소드를 가지고 있습니다.

</details>

<details>
  <summary>@JsonIgnoreProperties</summary>

## @JsonIgnoreProperties

- `@JsonIgnoreProperties` 는 Jackson 라이브러리에서 제공하는 어노테이션으로 json에 있지만 java오브젝트에 매칭되는 변수명이 없을때 발생하는 에러를 무시해줍니다.<br>
  이러한 에러는 아래 설정을 통해서 발생시킬 수 있습니다.
```java
ObjectMapper mapper = new ObjectMapper();
mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
```
- `@JsonProperty` 는 Jackson 라이브러리에서 제공하는 어노테이션으로 json과 자바오브젝트의 변수 이름이 매칭되지 않을때 매칭시켜 줍니다.
```java
@Getter
@Setter
public class Person {
    @JsonProperty("full_name")
    private String name;
    private int age;
}
```


</details>

<details>
  <summary>RestTemplate</summary>

## RestTemplate

Rest서버에 요청을 보내 Rest클라이언트의 기능을 만들어줍니다.<br>
GUI기반의 Rest클라이언트는 Postman, Insomnia 같은것들이 있습니다.

Http요청을 보내기 위해서 `RestTemplate`객체를 코드 블록에서 직접 생성하거나
```java
RestTemplate rt = new RestTemplate();
```
Bean에 등록해서 의존성을 주입받아 사용합니다.
```java
  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
  }
```

`RestTemplate`를 이용한 Http의 기본적인 요청 방법은 아래와 같습니다.
- Get
```java
String result = restTemplate.getForObject("https://api.example.com/data", String.class);
```
- Post
```java
MyRequestObject request = new MyRequestObject("data");
MyResponseObject response = restTemplate.postForObject("https://api.example.com/data", request, MyResponseObject.class);
```
- 인증
```java
restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("username", "password"));
```
- 에러 처리
```java
restTemplate.setErrorHandler(new MyCustomErrorHandler());
```
- exchange
  - HTTP 메서드, 요청 엔터티, 응답 타입 등을 명시적으로 지정
  - 반환 타입은 `ResponseEntity<T>`

```java
HttpHeaders headers = new HttpHeaders();
headers.setContentType(MediaType.APPLICATION_JSON);
MyRequestObject request = new MyRequestObject("data");
HttpEntity<MyRequestObject> entity = new HttpEntity<>(request, headers);

ResponseEntity<MyResponseObject> response = restTemplate.exchange(
    "https://api.example.com/data",
    HttpMethod.POST,
    entity, // get 이라면 null
    MyResponseObject.class
);
MyResponseObject responseBody = response.getBody();

```
또는 카카오에 요청할때 사용한 방법으로 Map을 이용해서 요청한다.
```java
  HttpHeaders headers = new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

  MultiValueMap<String, String> xForm = new LinkedMultiValueMap<>();
  xForm.add("grant_type", "authorization_code");
  xForm.add("client_id", "받은 키");
  xForm.add("redirect_uri", "http://localhost:8080/callback");
  xForm.add("code", code);

  HttpEntity<?> httpEntity = new HttpEntity<>(xForm,headers);
  ResponseEntity<String> responseEntity = rt.exchange(
        kakaoUrl,
        HttpMethod.POST,
        httpEntity,
        String.class);

  String responseBody = responseEntity.getBody();
```


</details>

<details>
  <summary>CommandLineRunner</summary>

## CommandLineRunner

애플리케이션 시작 시 실행되어야 하는 코드를 정의합니다.

```java
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> { // 람다 표현식의 관용적인 형태
			Quote quote = restTemplate.getForObject(
					"http://localhost:8080/api/random", Quote.class);
			log.info(quote.toString());
		};
	}
```
8080포트로 열려있는 서버에 요청을 보내 받은 응답을 `Quote`로 받고 로그를 출력합니다.

</details>

<details>
  <summary>@EnableScheduling</summary>

## @EnableScheduling

`@EnableScheduling` 어노테이션을 Spring Configuration 클래스에 추가한다면
`@Scheduled` 어노테이션이 붙은 메서드를 자동으로 찾아 주기적으로 실행합니다.

```java
@SpringBootApplication
@EnableScheduling
public class SpringbreakingApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringbreakingApplication.class, args);
  }
}
```
아래 메소드는 `bean`으로 등록된 클래스 내부에 있어야 합니다.
```java
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
```

</details>

<details>
  <summary>Gradle 빌드</summary>

## Gradle 

java기반 프로젝트 관리 도구인 Maven의 장황한 설정을 보완하기 위해 나온 빌드 도구입니다.

아래 커맨드로 gradle로 실행 가능한 작업 목록을 볼 수 있습니다.
```java
$ gradle tasks
```

```java
Starting a Gradle Daemon (subsequent builds will be faster)

> Task :tasks

------------------------------------------------------------
Tasks runnable from root project 'springbreaking'
------------------------------------------------------------

Application tasks
-----------------
bootRun - Runs this project as a Spring Boot application.

Build tasks
-----------
assemble - Assembles the outputs of this project.
bootBuildImage - Builds an OCI image of the application using the output of the bootJar task
bootJar - Assembles an executable jar archive containing the main classes and their dependencies.
bootJarMainClassName - Resolves the name of the application's main class for the bootJar task.
bootRunMainClassName - Resolves the name of the application's main class for the bootRun task.
build - Assembles and tests this project.
buildDependents - Assembles and tests this project and all projects that depend on it.
buildNeeded - Assembles and tests this project and all projects it depends on.
classes - Assembles main classes.
clean - Deletes the build directory.
jar - Assembles a jar archive containing the main classes.
testClasses - Assembles test classes.

Build Setup tasks
-----------------
init - Initializes a new Gradle build.
wrapper - Generates Gradle wrapper files.

Documentation tasks
-------------------
javadoc - Generates Javadoc API documentation for the main source code.
Rules
-----
Pattern: clean<TaskName>: Cleans the output files of a task.
Pattern: build<ConfigurationName>: Assembles the artifacts of a configuration.

To see all tasks and more detail, run gradle tasks --all

To see more detail about a task, run gradle help --task <task>

BUILD SUCCESSFUL in 41s
1 actionable task: 1 executed

```

## Gradle Wrapper

gradle Wrapper 를 이용하면 gradle를 설치하지 않고 버전에 상관없이 일관된 빌드를 구성할 수 있습니다.

gradle 프로젝트를 생성할 때 소스를 확인하면 gradle wrapper가 포함된 gradle 디렉토리가 있습니다.

`gradle-wrapper.properties` 파일 내부
```java
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2.1-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```
wrapper를 이용할 때는 주로 아래 커맨드들을 이용합니다.
```java
$ ./gradlew run // java 애플리케이션 실행
```
```java
$ ./gradlew bootRun // SpringBoot 애플리케이션 실행
```

```java
$ ./gradlew bootJar // 실행 가능한 boot 기반 jar파일 생성
```
```java
$ ./gradlew clean build // build 디렉토리를 삭제하고 빋르 ( 테스트 진행 )
```
</details>

<details>
  <summary>JDBCTemplate</summary>

## JDBCTemplate

- java의 JDBC 코드를 단순화하여 공통적인 문제를 처리하기 위해 사용합니다.<br>
- JDBC를 사용할때 반복적으로 사용되는 연결, `PreparedStatement`, `ResultSet`같은 코드를 생략합니다.<br>
- JDBC의 `SQLException`을 스프링의 `DataAccessException`로 변홥합니다.<br>
- 리소스의 메모리를 자동으로 관리해주며 Batch를 처리할 수 있습니다.<br>



`JDBCTemplate` 를 이용하기 위해서는 아래 의존성이 필요합니다.
```java
implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'
```

JPA 의존성은 `spring-boot-starter-data-jdbc` 의존성을 포함합니다.
```java
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```

JDBCTemplate를 사용하기 위해서는 먼저 DataSource를 등록해야 합니다.
```java
private final JdbcTemplate jdbcTemplate;

public JdbcTemplateItemRepository(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
}
```

JPA의존성을 추가했다면 yml의 Datasource 설정을 바탕으로 자동 구성이 이루어집니다.
(해당 드라이버 필요)

JDBCTemplate의 메소드로는
  INSERT, UPDATE, DETELTE, queryForObject, queryForInt, RowMapper, QUERY, EXECUTE, BATCH 같은 방법들이 있습니다.
( 추후 자세히 알아보자 )

</details>

<details>
  <summary>파일 업로드</summary>

## 파일 업로드

파일을 업로드 하기 위해서 최대 업로드 크기를 설정합니다.
```yml
spring:
  servlet:
    multipart:
      max-file-size: 128KB # 최대 크기 제한
      max-request-size: 128KB # formData 요청 크기 제한
```




</details>

<details>
  <summary>@ConfigurationProperties</summary>

## @ConfigurationProperties

스프링 부트에서 제공하는 어노테이션으로 외부 설정 파일의 ( `properties` or `yml` ) 속성을 java객체에 바인딩할 때 사용됩니다.

`(prefix = "app")`를 붙여서 특정 키워드로 시작하는 속성을 가져올 수도 있습니다.
```java
@ConfigurationProperties("storage") // (prefix = "storage")
@Getter
@Setter
public class StorageProperties {

	// 디폴트 값
	private String location = "upload-dir";
}
```
`properties` or `yml` 설정


```yml
storage:
  location: D:\ // path
```
위 접두사 설정에 따라 연결됩니다.

`@ConfigurationProperties`을 설정한 클래스의 필드를 가져오면 외부에서 설정한 속성을 java객체 내부로 가져올 수 있습니다.


</details>

<details>
  <summary>@Value 차이 (Lombok / org.springframework.beans.factory.annotation.Value)</summary>

## Lombok

롬복에서 사용하는 `@value`어노테이션은 필드를 불변하게 만들때 사용합니다.<br>
`private`, `final` 속성과 `Getter`, `equals()`, `hashCode()`, `toString()` 를 생성합니다.

## org.springframework.beans.factory.annotation.Value
스프링에서 제공하는 `@Value` 어노테이션은 외부 설정값을 가져올 때 사용합니다.
예를들어 위에서 외부설정 값을
```agsl
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {

	/**
	 * The actual value expression such as <code>#{systemProperties.myProp}</code>
	 * or property placeholder such as <code>${my.app.myProp}</code>.
	 */
	String value();

}
```
```agsl
@Configuration
public class StorageConfig {

    @Value("${storage.location}")
    private String storageLocation;
    
    @Bean
    public Path storagePath() {
        return Paths.get(storageLocation);
    }
}
```
</details>

<details>
  <summary>서비스 인터페이스와 구현클래스 분리</summary>

## 서비스 인터페이스와 구현클래스 분리

개발을 하다보면 참고할 예제나 공식문서 그리고 설계된 프로젝트를 보면 `bean`으로 등록될 서비스는 추상화된 서비스 인터페이스를 구현하고 있는 경우가 많습니다. <br>
추상화된 서비스 인터페이스와 구현 클래스를 분리하는 이유는
 - 가독성, 서비스에 어떤 메소드가 구현되어야 하는데 한눈에 보기 좋습니다.
 - 추상화, 메소드를 추상화시켜 다양한 구현으로 필요에 따라 코드를 교체할 수 있습니다.
 - 확장성, 마찬가지로 설계 방향에 따라 다른 구현 클래스를 만들어 적용시킬 수 있습니다.
 - 협업성, 구현해야할 메소드를 정해두면 구현 클래스는 모든 메소드를 구현해야 합니다.
```agsl
public interface StorageService {

	void init(); // 인터페이스의 모든 메소드는 public 속성을 가짐
	// ... 다른 메소드들
}
```
```agsl
@Service
@RequiredArgsConstructor
public class FileSystemStorageServiceImpl implements StorageService {

	private final Path rootLocation;

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
	// ... 구현된 메소드들
}
```
</details>

