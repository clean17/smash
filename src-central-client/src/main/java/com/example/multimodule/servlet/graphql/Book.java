package com.example.multimodule.servlet.graphql;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * record - 불변 클래스 정의
 *
 * 모든 필드가 final 속성을 가진다.
 * 모든 필드에 getter 메소드를 제공한다.
 * equals(), hashCode(), toString() 메서드를 자동으로 오버라이드
 * 기본 생성자 제공
 */
//public record Book (String id, String name, int pageCount, String authorId) {
//
//    private static List<Book> books = Arrays.asList(
//            new Book("book-1", "Effective Java", 416, "author-1"),
//            new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
//            new Book("book-3", "Down Under", 436, "author-3")
//    );
//
//    public static Book getById(String id) {
//        return books.stream()
//				.filter(book -> book.id().equals(id))
//				.findFirst()
//				.orElse(null);
//    }
//}
	// findFirst - 순차스트림, findAny - 병렬스트림용 순차라면 동일한 응답


@Getter
@AllArgsConstructor
public class Book {
	private final String id;
	private final String name;
	private final int pageCount;
	private final String authorId;

	private static List<Book> books = Arrays.asList(
			new Book("book-1", "Effective Java", 416, "author-1"),
			new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
			new Book("book-3", "Down Under", 436, "author-3")
	);

	public static Book getById(String id) {
		return books.stream()
				.filter(book -> book.getId().equals(id))
				.findFirst()
				.orElse(null);
	}
}
