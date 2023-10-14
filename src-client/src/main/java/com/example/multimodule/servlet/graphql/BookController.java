package com.example.multimodule.servlet.graphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

/**
 * graphql이 자동으로 만들어 주는 URI ( 테스트용 엔드포인트 )
 * http://localhost:8080/graphiql?path=/graphql
 *
 * 웹페이지에 접속해서 요청할 쿼리문
 * query bookDetails {
 *   bookById(id: "book-1") {
 *     id
 *     name
 *     pageCount
 *     author {
 *       id
 *       firstName
 *       lastName
 *     }
 *   }
 * }
 *
 * graphql이 응답하는 json
 * {
 *   "data": {
 *     "bookById": {
 *       "id": "book-1",
 *       "name": "Effective Java",
 *       "pageCount": 416,
 *       "author": {
 *         "id": "author-1",
 *         "firstName": "Joshua",
 *         "lastName": "Bloch"
 *       }
 *     }
 *   }
 * }
 */
@Controller
public class BookController {
    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}