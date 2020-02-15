package com.example.newspape.service.Imp;

import com.example.newspape.bean.Book;
import com.example.newspape.config.CacheUpdateRunner;
import com.example.newspape.mapper.BookMapper;
import com.example.newspape.service.BookService;
import io.lettuce.core.api.sync.RedisKeyCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    CacheUpdateRunner cacheUpdateRunner;
    @Override
    //redis
    public List<Book> getAllBooks() {
        RedisSerializer redisSerializer=new StringRedisSerializer();
        redisTemplate.setKeySerializer(redisSerializer);
        List<Book> list= (List<Book>) redisTemplate.opsForValue().get("allBooks");
        if(list==null)
        {
            synchronized (this){
                list= (List<Book>) redisTemplate.opsForValue().get("allBooks");
                if(list==null)
                {
                    List<Book> allBooks = bookMapper.getAllBooks();
                    redisTemplate.opsForValue().set("allBooks",allBooks);
                    return allBooks;
                }else{
                    return list;
                }
            }
        }else{
            return list;
        }
    }
    //redis
    @Override
    public void deletBookById(Integer id) {
        redisTemplate.delete("allBooks");
        bookMapper.deletBookById(id);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allBooks");
        }
    }

    @Override
    public Book getByBookId(Integer id) {
        return bookMapper.getByBookId(id);
    }
    //redis
    @Override
    public void updateBookByBook(Book book) {
        redisTemplate.delete("allBooks");
        bookMapper.updateBookByBook(book);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allBooks");
        }
    }
    //redis
    @Override
    public void addBook(String name,String author,String press,String date,Double price,String category,Integer inventory,MultipartFile file,String text) {
        System.out.println("--------------------------");
        Book book=new Book();
        Set<String> keys = redisTemplate.keys("allBooks");
        redisTemplate.delete(keys);
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //System.out.println("6666666666666666666666666666");
        String filePath = "D:\\javaee\\newspape\\src\\main\\resources\\static\\img\\";
        File dest = new File(filePath + fileName);
        System.out.println("---------------------");
        System.out.println(dest);
        System.out.println("---------------------");
        System.out.println(dest);
        try {
            file.transferTo(dest);
            System.out.println("上传成功");
        } catch (IOException e) {
            System.out.println("上传失败");
        }
        String url="../static/img/"+fileName;
        book.setAuthor(author);
        book.setCategory(category);
        book.setDate(date);
        book.setInventory(inventory);
        book.setName(name);
        book.setPress(press);
        book.setPrice(price);
        book.setText(text);
        book.setUrl(url);
        bookMapper.addBook(book);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            keys = redisTemplate.keys("allBooks");
            redisTemplate.delete(keys);
        }
    }

    @Override
    public List<Book> getAllBooks2() {
        return bookMapper.getAllBooks2();
    }

    @Override
    public List<Book> getByCate(String category) {
        return bookMapper.getByCate(category);
    }

    @Override
    public void updateBookHeat(Integer id, Integer heat) {
        redisTemplate.delete("allBooks");
        bookMapper.updateBookHeat(id,heat);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allBooks");
        }
    }

    @Override
    public List<Book> getAllBooks1() {
        return bookMapper.getAllBooks1();
    }

    @Override
    public List<Book> getNewBook() {
        return bookMapper.getNewBook();
    }

    @Override
    public List<Book> getByKeyword(String keyword) {
        return bookMapper.getByKeyword(keyword);
    }

    @Override
    public int getInventoryById(Integer id) {
        return bookMapper.getInventoryById(id);
    }

    @Override
    public void updateInventory(Integer id) {
        redisTemplate.delete("allBooks");
        bookMapper.updateInventory(id);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            redisTemplate.delete("allBooks");
        }
    }

    @Override
    public int getInventoryByName(String name) {
        return bookMapper.getInventoryByName(name);
    }

    @Override
    public void updateInventory02(String name) {
        bookMapper.updateInventory02(name);
    }
}
