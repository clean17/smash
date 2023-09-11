package com.example.springbreaking.managingTransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.util.Assert;

@RequiredArgsConstructor
//@Component
@Slf4j
class AppRunner implements CommandLineRunner {

  private final BookingService bookingService;

  /**
   * run 메소드를 오버라이딩해야 자동 실행
   * 
   * 여기서 사용되는 DB의 칼럼은 varchar(5) NOT NULL - 5자를 넘길 수 없다.
   * @param args incoming main method arguments
   * @throws Exception
   */
  @Override
  public void run(String... args) throws Exception {
    bookingService.book("Alice", "Bob", "Carol");
    Assert.isTrue(bookingService.findAllBookings().size() == 3,
        "First booking should work with no problem");
    log.info("Alice, Bob and Carol have been booked");
    try {
      bookingService.book("Chris", "Samuel"); // 트랜잭션 걸려있으므로 Chris도 롤백
    } catch (RuntimeException e) {
      log.info("v--- The following exception is expect because 'Samuel' is too " +
          "big for the DB ---v");
    }

    for (String person : bookingService.findAllBookings()) {
      log.info("So far, " + person + " is booked."); // 롤백되었으므로 3명밖에 출력되지 못함
    }
    log.info("You shouldn't see Chris or Samuel. Samuel violated DB constraints, " +
        "and Chris was rolled back in the same TX"); 
    Assert.isTrue(bookingService.findAllBookings().size() == 3,
        "'Samuel' should have triggered a rollback");

    try {
      bookingService.book("Buddy", null); // NOT NULL 제약에 걸린다 - 롤백
    } catch (RuntimeException e) {
      log.info("v--- The following exception is expect because null is not " +
          "valid for the DB ---v");
      log.error(e.getMessage());
    }

    for (String person : bookingService.findAllBookings()) {
      log.info("So far, " + person + " is booked."); // 마찬가지로 롤백되었으므로 3명만 출력 됨
    }
    log.info("You shouldn't see Buddy or null. null violated DB constraints, and " +
        "Buddy was rolled back in the same TX");
    Assert.isTrue(bookingService.findAllBookings().size() == 3,
        "'null' should have triggered a rollback");
  }

}