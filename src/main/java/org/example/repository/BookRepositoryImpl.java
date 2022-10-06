package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query("SELECT * FROM BOOK", new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void save(Book book) {
        int id = getAll().stream()
                .map(Book::getId)
                .max(Integer::compare)
                .orElse(0) + 1;

        jdbcTemplate.update("INSERT INTO BOOK VALUES(?, ?, ?, ?)",
                id,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }
}
