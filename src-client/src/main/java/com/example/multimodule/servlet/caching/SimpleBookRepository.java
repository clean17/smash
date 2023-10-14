package com.example.multimodule.servlet.caching;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

  /**
   * 캐싱되었으므로 실제 객체에 한번 접근 후 반환된 데이터를 메모리에 캐싱후 다음 요청시 메모리에서 가져간다.
   * 두번 호출되지 않는다.
   * @param isbn
   * @return
   */
  @Override
  @Cacheable("books")
  public Book getByIsbn(String isbn) {
    simulateSlowService();
    return new Book(isbn, "Some book");
  }

  // Don't do this at home
  private void simulateSlowService() {
    try {
      long time = 2000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

}