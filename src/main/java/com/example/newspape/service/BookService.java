package com.example.newspape.service;

import com.example.newspape.bean.Book;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public void deletBookById(Integer id);
    public Book getByBookId(Integer id);
    public void updateBookByBook(Book book);
    public void addBook(String name,String author,String press,String date,Double price,String category,Integer inventory,MultipartFile file,String text);
    public List<Book> getAllBooks2();
    public List<Book> getByCate(String category);
    public void updateBookHeat(Integer id,Integer heat);
    public List<Book> getAllBooks1();
    public List<Book> getNewBook();
    public List<Book> getByKeyword(String keyword);
    public int getInventoryById(Integer id);
    public void updateInventory(Integer id);
    public int getInventoryByName(String name);
    public void updateInventory02(String name);
}
