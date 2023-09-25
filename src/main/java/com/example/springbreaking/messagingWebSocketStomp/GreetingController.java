package com.example.springbreaking.messagingWebSocketStomp;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

  /**
   * @MessageMapping - 웹소켓을 매핑 - 생산자가 연결할 주소
   * @SendTo - 해당 주소로 응답을 반환한다 - 수신자가 구독할 주소
   * SendTo를 구독한 모든 유저에게 메세지를 전달
   *
   * @param message
   * @return
   * @throws Exception
   */
  @MessageMapping("/hello")
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(10); // simulated delay
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }
}