package com.example.springbreaking.graphql;

//public record Author (String id, String firstName, String lastName) {
//
//    private static List<Author> authors = Arrays.asList(
//            new Author("author-1", "Joshua", "Bloch"),
//            new Author("author-2", "Douglas", "Adams"),
//            new Author("author-3", "Bill", "Bryson")
//    );
//
//    public static Author getById(String id) {
//        return authors.stream()
//				.filter(author -> author.id().equals(id))
//				.findFirst()
//				.orElse(null);
//    }
//}

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor

public class Author {
    private final String id;
    private final String firstName;
    private final String lastName;

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joshua", "Bloch"),
            new Author("author-2", "Douglas", "Adams"),
            new Author("author-3", "Bill", "Bryson")
    );

    public static Author getById(String id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
