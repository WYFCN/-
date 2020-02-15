package com.example.newspape.mapper;

import com.example.newspape.bean.Book;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {
    @Select("select * from book")
    public List<Book> getAllBooks();
    @Select("select * from book order by heat desc limit 10")
    public List<Book> getAllBooks1();
    @Select("select * from book limit 4")
    public List<Book> getAllBooks2();
    @Delete("delete from book where id=#{id}")
    public void deletBookById(Integer id);
    @Select("select * from book where id=#{id}")
    public Book getByBookId(Integer id);
    @Update("update book set name=#{name},author=#{author},press=#{press},date=#{date},price=#{price} where id=#{id}")
    public void updateBookByBook(Book book);
    @Insert("insert into book(name,author,press,date,price,text,url,category,inventory) values(#{name},#{author},#{press},#{date},#{price},#{text},#{url},#{category},#{inventory})")
    public void addBook(Book book);
    @Select("select * from book where category=#{category}")
    public List<Book> getByCate(String category);
    @Update("update book set heat=#{heat} where id=#{id}")
    public void updateBookHeat(Integer id,Integer heat);
    @Select("select * from book order by date desc")
    public List<Book> getNewBook();
    @Select("select * from book where name like '%${keyword}%'")
    public List<Book> getByKeyword(String keyword);
    @Select("select inventory from book where id=#{id} ")
    public int getInventoryById(Integer id);
    @Update("update book set inventory=inventory-1 where id=#{id}")
    public void updateInventory(Integer id);
    @Update("update book set inventory=inventory-1 where name=#{name}")
    public void updateInventory02(String name);
    @Select("select inventory from book where name=#{name}")
    public int getInventoryByName(String name);
}
