package com.example.multimodule.servlet.caching;

public interface BookRepository {
  Book getByIsbn(String isbn);

}