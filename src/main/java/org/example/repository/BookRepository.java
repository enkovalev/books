package org.example.repository;

import org.example.model.Book;

import java.util.List;

public interface BookRepository {
    /**
     * Получить все книги
     * @return Список книг
     */
    List<Book> getAll();

    /**
     * Сохранить книгу
     * @param book Книга
     */
    void save(Book book);
}
