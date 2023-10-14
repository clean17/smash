package com.example.multimodule.servlet.messagingRedis;

/**
 * 리스너 등록 및 메세지 전송 테스트
 */
/*
@Configuration
public class MessagingRedisApplication {

	*/
/**
	 * Redis 메세지 리스너 컨테이너
	 * 'chat' 토픽의 메세지를 수신한다.
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 *//*

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

		return container;
	}

	*/
/**
	 * 메세지 리스너 어뎁터
	 * Reciver객체의 'receiveMessage'메소드를 사용한다.
	 * @param receiver
	 * @return
	 *//*

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	*/
/**
	 * Redis 메시지 수신기
	 * 메세지를 수신하고 카운트 증가
	 * @return 
	 *//*

	@Bean
	Receiver receiver() {
		return new Receiver();
	}

	*/
/**
	 * RedisConnectionFactory -> StringRedisTemplate 생성 및 반환
	 * StringRedisTemplate : Redis와의 문자열 통신을 담당
	 * @param connectionFactory
	 * @return
	 *//*

	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

}*/
