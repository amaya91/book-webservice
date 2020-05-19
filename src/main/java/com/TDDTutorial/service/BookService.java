package com.TDDTutorial.service;

import com.TDDTutorial.exception.RecordNotFoundException;
import com.TDDTutorial.model.Book;
import com.TDDTutorial.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {

        Iterable<Book> books = bookRepository.findAll();

        List<Book> listOfBooks = new ArrayList<Book>();
        books.forEach(listOfBooks::add);

        return listOfBooks;
    }

    public Book getBookById(int id) throws RecordNotFoundException {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isPresent()) {
            return book.get();
        } else {
            throw new RecordNotFoundException("No book exists for given id");
        }

    }

    public String saveBook(Book book) {

        bookRepository.save(book);

        return "Successful Save";
    }

    public String deleteBook(int id) {

        bookRepository.deleteById(id);

        return "Successful Delete";

//        Optional<Book> book = bookRepository.findById(id);
//
//        if(book.isPresent()) {
//            bookRepository.deleteById(id);
//            return "Successful Delete";
//
//        } else {
//            return "No book exists for given id";
//        }
    }
}
