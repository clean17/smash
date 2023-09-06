package com.example.springbreaking.rest;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RepresentationModel : Hateoas의 리소스 표현 기본 클래스, 링크 정보를 포함시켜 준다.
 * 또한 링크를 조작하는 메소드를 제공한다.
 * 아래처럼 상속해서 추가적인 속성을 부여할 수 있다.
 */
public class Greeting extends RepresentationModel<Greeting> {

    private final String content;

    /**
     * JsonCreator : Jackson라이브러리에게 POJO의 인스턴스를 생성할 수 있는 방법을 알려준다.
     *
     * 일반적으로 Jackson라이브러리는 json으로 객체를 만들때 기본생성자로 객체를 만들고 Setter로 데이터를 주입한다.
     * 아래처럼 객체가 불변이 된다면 Setter로 주입할 수 없다.
     * 이 때는 @JsonCreator와 @JsonProperty를 사용해서 객체를 생성한다.
     *
     * @param content
     */
    @JsonCreator
    public Greeting(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
