/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.springbreaking.servlet.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;


/**
 * 예약된 작업 만들기
 *
 *
 */
@Component
public class ScheduledTasks {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    // 5초 마다 실행 - spring-boot-starter 에 포함된 어노테이션
    // @Scheduled(cron=". . ."). 와 같은 정교한 작업도 가능
/*	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		log.info("The time is now {}", dateFormat.format(new Date()));
	}*/
}
