package org.example;

import lombok.RequiredArgsConstructor;
import org.example.model.Book;
import org.example.model.BookStatistic;
import org.example.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> read() {
        final List<Book> books = bookService.getSortedBooks();

        return books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/books")
    public ResponseEntity<?> create(@RequestBody Book book) {
        bookService.addBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/grouped-books")
    public ResponseEntity<?> getGroupedBooks() {
        final List<Book> books = bookService.getGroupedBooks();

        return books != null &&  !books.isEmpty()
                ? new ResponseEntity<>(books, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/book-statistics/{symbol}")
    public ResponseEntity<List<BookStatistic>> read(@PathVariable(name = "symbol") char symbol) {
        final List<BookStatistic> bookStatistics = bookService.getBookStatistics(symbol);

        return bookStatistics != null
                ? new ResponseEntity<>(bookStatistics, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
