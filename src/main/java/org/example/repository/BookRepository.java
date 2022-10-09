package org.example.repository;

import org.example.model.Book;
import org.example.model.BookStatistic;

import java.util.List;

public interface BookRepository {
    /**
     * Получить все книги
     * @return Список книг
     */
    List<Book> getAll();

    /**
     * Получить список книг отсортированный в обратном алфавитном порядке значения колонки book.title
     * @return Список книг
     */
    List<Book> getSortedBooks();

    /**
     * Сохранить книгу
     * @param book Книга
     */
    void save(Book book);

    /**
     * Собрать статистику по авторам
     * @param symbol символ
     * @return Список из максимум 10 авторов
     */
    List<BookStatistic> getBookStatistics(char symbol);
}
