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
        Integer id = jdbcTemplate.queryForObject("SELECT MAX(id) FROM BOOK", Integer.class);

        jdbcTemplate.update("INSERT INTO BOOK VALUES(?, ?, ?, ?)",
                id == null ? 1 : id + 1,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }
}
