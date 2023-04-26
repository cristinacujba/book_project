package com.example.book_project.service;

import com.example.book_project.model.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    void saveBook(Book book);
    Book getBookById(int id);
    void deleteBookById(int id);
    Page<Book> findPaginated(int pageNo , int pageSize);
}
