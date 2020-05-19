package com.TDDTutorial.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class BookTest {

    @Test
    public void BookTest() {
        Book book = new Book();

        Assert.assertEquals(0, book.getId());
        Assert.assertNull(book.getTitle());
        Assert.assertNull(book.getAuthor());
        Assert.assertNull(book.getGenre());
        Assert.assertNull(book.getPublisher());
    }
}
