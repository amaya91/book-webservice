package com.TDDTutorial.controller;

import com.TDDTutorial.model.Book;
import com.TDDTutorial.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private Book book = new Book();

    @Test
    public void getAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        book.setAuthor("John");
        books.add(book);
        when(bookService.getAllBooks()).thenReturn(books);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].author", is(book.getAuthor())));
        verify(bookService, times(1)).getAllBooks();
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void getBookById() throws Exception {
        book.setAuthor("John");
        book.setId(123);
        when(bookService.getBookById(book.getId())).thenReturn(book);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.author", is(book.getAuthor())))
                .andExpect(jsonPath("$.id", is(book.getId())));
        verify(bookService, times(1)).getBookById(book.getId());
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void createBook() throws Exception {
        Mockito.when(bookService.saveBook(book)).thenReturn("Successful Save");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getBooksInJson(1));
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).saveBook(book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void updateBook() throws Exception {
        Mockito.when(bookService.saveBook(book)).thenReturn("Successful Save");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(getBooksInJson(1));
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).saveBook(book);
        verifyNoMoreInteractions(bookService);
    }

    @Test
    public void deleteBook() throws Exception {
        book.setId(123);
        Mockito.when(bookService.deleteBook(book.getId())).thenReturn("Successful Delete");

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/" + book.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).deleteBook(book.getId());
        verifyNoMoreInteractions(bookService);
    }

    private String getBooksInJson(int x) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < x; i++) {
            if (i == x - 1) {
                str.append("{\"id\":null, \"author\":null, \"title\":null, \"publisher\":null, \"genre\":null }");
            } else {
                str.append("{\"id\":null, \"author\":null, \"title\":null, \"publisher\":null, \"genre\":null }, ");            }
        }
        return str.toString();
    }
}
