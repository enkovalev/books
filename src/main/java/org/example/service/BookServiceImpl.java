package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.model.BookStatistic;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository repository;

    @Override
    public List<Book> getSortedBooks() {
        List<Book> books = repository.getAll();
        books.sort(Comparator.comparing(Book::getTitle).reversed());
        return books;
    }

    @Override
    public void addBook(Book book) {
        repository.save(book);
    }

    @Override
    public List<Book> getGroupedBooks() {
        List<Book> books = repository.getAll();
        Map<String, List<Book>> authorMap = new HashMap<>();

        books.forEach(book -> groupingBooks(authorMap, book));

        return authorMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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
        Map<String, Integer> statisticsMap = new HashMap<>();
        List<Book> books = repository.getAll();

        books.forEach(book -> addBookToStatistic(statisticsMap, book, symbol));

        List<BookStatistic> bookStatistics = new ArrayList<>();
        statisticsMap.forEach((autor, amount) -> bookStatistics.add(new BookStatistic(autor, amount)));

        bookStatistics.sort(Comparator.comparingInt(BookStatistic::getAmountSymbols));

        return bookStatistics.size() > 10 ? bookStatistics.subList(0, 9) : bookStatistics;
    }

    private void addBookToStatistic(Map<String, Integer> statisticsMap, Book book, char symbol) {
        Integer count = statisticsMap.get(book.getAuthor());
        int amount = book.getStatistic(symbol);
        if (amount == 0) {
            return;
        }

        if (count == null) {
            statisticsMap.put(book.getAuthor(), amount);
        } else {
            statisticsMap.put(book.getAuthor(), count + amount);
        }
    }
}
