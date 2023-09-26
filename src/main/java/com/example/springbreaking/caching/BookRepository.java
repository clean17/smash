package com.example.springbreaking.caching;

public interface BookRepository {
  Book getByIsbn(String isbn);

}