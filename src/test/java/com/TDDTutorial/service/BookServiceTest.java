package com.TDDTutorial.service;

import com.TDDTutorial.exception.RecordNotFoundException;
import com.TDDTutorial.model.Book;
import com.TDDTutorial.repository.BookRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = BookServiceTest.BookServiceTestContextConfiguration.class)
public class BookServiceTest {

    @TestConfiguration
    static class BookServiceTestContextConfiguration {
        @Bean
        public BookService bookService() {
            return new BookService();
        }
    }

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    private Book book;

    @Before
    public void init() {
        book = new Book();
    }


    @Test
    public void getAllBooks() {
        List<Book> books = new ArrayList<>();

        book.setId(12345);
        books.add(book);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        Iterable<Book> actual = bookService.getAllBooks();
        Assert.assertEquals(actual, books);
    }

    @Test
    public void getBookById() throws RecordNotFoundException {
        int id = 123;
        book.setId(id);
        Mockito.when(bookRepository.findById(book.getId())).thenReturn(Optional.ofNullable(book));

        Book actual = bookService.getBookById(id);
        Assert.assertEquals(book, actual);
        Assert.assertEquals(id, actual.getId());
    }

    @Test
    public void saveBook() {
        Mockito.when(bookRepository.save(book)).thenReturn(null);

        String response = bookService.saveBook(book);

        verify(bookRepository).save(book);
        Assert.assertEquals("Successful Save", response);
    }

    @Test
    public void deleteBook() {
        int id = 123;
        doNothing().when(bookRepository).deleteById(id);

        String actual = bookService.deleteBook(id);

        verify(bookRepository, times(1)).deleteById(id);
        Assert.assertEquals("Successful Delete", actual);
    }
}