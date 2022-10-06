package org.example.service;

import org.example.model.Book;
import org.example.model.BookStatistic;

import java.util.List;

/**
 * Сервис работы с книгами
 */
public interface BookService {

    /**
     * Получить список книг отсортированный в обратном алфавитном порядке значения колонки book.title
     * @return Отсортированный список книг
     */
    List<Book> getSortedBooks();

    /**
     * Добавление книги
     * @param book Книга
     */
    void addBook(Book book);

    /**
     * Получить список книг сгрупированный по автору
     * @return Групированный список
     */
    List<Book> getGroupedBooks();

    /**
     * Собрать статистику по авторам
     * @param symbol символ
     * @return Список из максимум 10 авторов
     */
    List<BookStatistic> getBookStatistics(char symbol);
}
