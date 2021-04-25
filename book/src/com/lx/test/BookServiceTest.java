package com.lx.test;

import com.lx.pojo.Book;
import com.lx.pojo.Page;
import com.lx.service.BookService;
import com.lx.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "小棉袄养成计划！", new BigDecimal(9999), "未知", 9999, 100, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(7);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23, "小棉袄养成计划！", new BigDecimal(9999), "未知", 9999, 2, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE ));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE,10,50 ));
    }
}