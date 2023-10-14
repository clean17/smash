package com.example.multimodule.servlet.managingTransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component // 추상
@Slf4j
public class BookingService {

  // JdbcTemplate 은  Spring의 spring-jdbc 모듈에 포함되어 있습니다.
  private final JdbcTemplate jdbcTemplate;

  /**
   * 트랜잭션이 걸리면 롤백
   * @param persons
   */
  @Transactional
  public void book(String... persons) {
    for (String person : persons) {
      log.info("Booking " + person + " in a seat...");
      jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
    }
  }

  public List<String> findAllBookings() {
    return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
        (rs, rowNum) -> rs.getString("FIRST_NAME"));
  }

}