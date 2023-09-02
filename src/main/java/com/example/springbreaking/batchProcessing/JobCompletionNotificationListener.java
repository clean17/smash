package com.example.springbreaking.batchProcessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 완료된 작업의 알림을 받음
 *
 * JobExecution: 배치 작업이 실행될 때 마다 인스턴스 생성
 * - 상태 정보 유지 - STARTING, STARTED, STOPPING, STOPPED, FAILED, COMPLETED, ABANDONED
 * - 실행 메타 데이터, 스텝 실행 정보, 예외 정보, 외부 컨텍스트 정보, 연결된 배치 작업등을 저장함
 */
@Component
@Slf4j
public class JobCompletionNotificationListener implements JobExecutionListener {

  private final JdbcTemplate jdbcTemplate;

  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("!!! JOB START !!!");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if(jobExecution.getStatus() == BatchStatus.COMPLETED) { // 상태 - COMPLETED, STARTING, STARTED, STOPPING, STOPPED, FAILED, ABANDONED, UNKNOWN
      log.info("!!! JOB FINISHED! Time to verify the results");

      jdbcTemplate.query("SELECT first_name, last_name FROM people",
        (rs, row) -> new Person( // rs : ResultSet
                // 각 결과 row 마다 SELECT 쿼리의 프로젝션 순서를 지정하여 Person 객체 생성
          rs.getString(1),
          rs.getString(2))
      ).forEach(person -> log.info("Found <{{}}> in the database.", person));
    }
  }
}