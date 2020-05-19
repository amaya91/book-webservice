package com.TDDTutorial.controller;

import com.TDDTutorial.exception.RecordNotFoundException;
import com.TDDTutorial.model.Book;
import com.TDDTutorial.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/")
public class BookController {

    private final BookService bookService;

    @Autowired
    private BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {

        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) throws RecordNotFoundException {

        return bookService.getBookById(id);
    }

    @PostMapping
    public String createBook(@RequestBody Book book) {

        return bookService.saveBook(book);
    }

    @PutMapping
    public String updateBook(@RequestBody Book book) {

        return bookService.saveBook(book);
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable int id) {
        return bookService.deleteBook(id);

    }



}
