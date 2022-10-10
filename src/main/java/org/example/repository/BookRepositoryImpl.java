package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.model.BookStatistic;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private static final String QUERY_ALL = "SELECT * FROM BOOK";

    private static final String QUERY_SORTED_BOOKS = "SELECT * FROM BOOK ORDER BY title desc";

    private static final String QUERY_SAVE = "INSERT INTO BOOK (title, author, description) VALUES(?, ?, ?)";

    private static final String QUERY_STATISTICS = "SELECT * FROM (" +
            "SELECT author AS author, sum(res) AS amountSymbols FROM (" +
            "SELECT author AS author, title, (length(title) - length(REPLACE(REPLACE(title, '{1}', ''), '{2}', ''))) AS res FROM BOOK) as atr " +
            "GROUP BY author HAVING sum(res) > 0" +
            ") as sum_table ORDER BY amountSymbols desc LIMIT 10";

    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<Book> getAll() {
        return jdbcTemplate.query(QUERY_ALL, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public List<Book> getSortedBooks() {
        return jdbcTemplate.query(QUERY_SORTED_BOOKS, new BeanPropertyRowMapper<>(Book.class));
    }

    @Override
    public void save(Book book) {
        jdbcTemplate.update(QUERY_SAVE,
                book.getTitle(),
                book.getAuthor(),
                book.getDescription());
    }

    @Override
    public List<BookStatistic> getBookStatistics(char symbol) {
        String queryStatistics = QUERY_STATISTICS
                .replace("{1}", (symbol + "").toLowerCase())
                .replace("{2}", (symbol + "").toUpperCase());
        return jdbcTemplate.query(queryStatistics, new BeanPropertyRowMapper<>(BookStatistic.class));
    }
}
