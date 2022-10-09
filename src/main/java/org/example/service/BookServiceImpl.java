package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.model.BookStatistic;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<Book> getSortedBooks() {
        return repository.getSortedBooks();
    }

    @Override
    public void addBook(Book book) {
        repository.save(book);
    }

    @Override
    public Map<String, List<Book>> getGroupedBooks() {
        List<Book> books = repository.getAll();
        Map<String, List<Book>> authorMap = new HashMap<>();

        books.forEach(book -> groupingBooks(authorMap, book));

        return authorMap;
    }

    private void groupingBooks(Map<String, List<Book>> authorMap, Book book) {
        List<Book> books = authorMap.get(book.getAuthor());
        if (books == null) {
            books = new ArrayList<>();
            books.add(book);
            authorMap.put(book.getAuthor(), books);
        } else {
            books.add(book);
        }
    }

    @Override
    public List<BookStatistic> getBookStatistics(char symbol) {
        return repository.getBookStatistics(symbol);
    }
}
