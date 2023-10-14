package com.example.multimodule.webFlux.reactiveRedis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Data 어노테이션은 보일러플레이트 코드를 최소화하기 위한 어노테이션으로
 *
 * @Getter, @Setter, @RequiredArgsConstructor,
 * @ToString, @EqualsAndHashcCode 의 기능을 수행합니다.
 *
 * 하지만 필요하지 않은 기능도 제공합니다. 예를들어 모든 필드가 게터, 세터가 필요하지 않다면 사용을 지양해야 합니다.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee {
  private String id;
  private String name;
}