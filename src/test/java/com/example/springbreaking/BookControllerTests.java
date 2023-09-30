package com.example.springbreaking;

import com.example.springbreaking.graphql.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

/**
 * graphql 관련 구성만 로드 + 프로젝트의 sdl 참조
 */
@GraphQlTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

//    @Test
//    void shouldGetFirstBook() {
//        this.graphQlTester
//				.documentName("bookDetails")
//				.variable("id", "book-1")
//                .execute()
//                .path("bookById")
//                .matchesJson("""
//                    {
//                        "id": "book-1",
//                        "name": "Effective Java",
//                        "pageCount": 416,
//                        "author": {
//                          "firstName": "Joshua",
//                          "lastName": "Bloch"
//                        }
//                    }
//                """);
//    }

    @Test
    void shouldGetFirstBook() {
        this.graphQlTester
                .documentName("bookDetails")
                .variable("id", "book-1")
                .execute()
                .path("bookById")
                .matchesJson("{\n" +
                        "    \"id\": \"book-1\",\n" +
                        "    \"name\": \"Effective Java\",\n" +
                        "    \"pageCount\": 416,\n" +
                        "    \"author\": {\n" +
                        "      \"firstName\": \"Joshua\",\n" +
                        "      \"lastName\": \"Bloch\"\n" +
                        "    }\n" +
                        "}");
    }

}