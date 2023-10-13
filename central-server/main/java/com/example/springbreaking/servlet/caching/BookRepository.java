package com.example.springbreaking.servlet.caching;

public interface BookRepository {
  Book getByIsbn(String isbn);

}