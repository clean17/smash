package com.example.springbreaking.servlet.asyncMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Jackson1.x 버전에서
 * json을 역직렬화할때 발생하는 에러룰 무시 -> ignoreUnknown=true
 *
 * 기존에는 json 속성이 java 객체에 없다면
 * UnrecognizedPropertyException 에러가 발생
 *
 * Jackson2.x 버전부터 에러는 발생하지 않음
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {

  private String name;
  private String blog;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBlog() {
    return blog;
  }

  public void setBlog(String blog) {
    this.blog = blog;
  }

  @Override
  public String toString() {
    return "User [name=" + name + ", blog=" + blog + "]";
  }

}