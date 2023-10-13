package com.example.springbreaking.servlet.caching;

import java.io.Serializable;

public class Book implements Serializable {
  private static final long serialVersionUID = 1L;

  private String isbn;
  private String title;

  public Book(String isbn, String title) {
    this.isbn = isbn;
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "Book{" + "isbn='" + isbn + '\'' + ", title='" + title + '\'' + '}';
  }

}