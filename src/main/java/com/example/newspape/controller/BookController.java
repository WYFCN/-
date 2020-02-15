package com.example.newspape.controller;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;
import com.example.newspape.bean.User;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import com.example.newspape.util.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/books")
    public String toBookList(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询图书列表 及设置分页信息
        List<Book> books = bookService.getAllBooks();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 18, books);
        model.addAttribute("booksDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "bookList";
    }
    @GetMapping("/books1")
    public String bookList(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询用户列表 及设置分页信息
        List<Book> books = bookService.getAllBooks();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 10, books);
        model.addAttribute("books", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "books";
    }
    @PostMapping("/book/{id}")
    public String deleteBook(@PathVariable("id") Integer id)
    {
        bookService.deletBookById(id);
        return "redirect:/books1";
    }
    @GetMapping("/book/{id}")
    public String toUpdateBook(@PathVariable("id") Integer id, Model model)
    {
        Book book = bookService.getByBookId(id);
        model.addAttribute("b1",book);
        return "updateBook";
    }
    @PostMapping("/book1")
    public String updateBook(Book book)
    {
        System.out.println(book);
        bookService.updateBookByBook(book);
        return "redirect:/books1";
    }
    @GetMapping("/addbook")
    public String toAddBook()
    {
        return "addbook";
    }
    @PostMapping("/addbook")
    public String addBook(@RequestParam("name") String name,@RequestParam("author") String author,@RequestParam("press") String press,@RequestParam("date") String date,@RequestParam("price") Double price,@RequestParam("category") String category,@RequestParam("inventory") Integer inventory,@RequestParam("url") MultipartFile file,@RequestParam("text") String text)
    {
        System.out.println("333333333333333333333");
        if(file.isEmpty())
            System.out.println("777777777777");
        else
            System.out.println("*******************");
        System.out.println(name);
        try {
            bookService.addBook(name,author,press,date,price,category,inventory,file,text);
        }catch (Exception e)
        {
            System.out.println("报错了。。。。。。。。");
        }
        return "redirect:/books";
    }
    @GetMapping("/detail/{id}")
    public String toDetail(@PathVariable("id") Integer id, Model model, HttpServletRequest res)
    {
        String s = String.valueOf(id);
        System.out.println(s);
        String flag = (String) res.getSession().getAttribute(s);
        System.out.println(flag);
        Book book = bookService.getByBookId(id);
        if(null==flag)
       {
            res.getSession().setAttribute(s,s);
            int heat=book.getHeat();
            heat=heat+1;
            bookService.updateBookHeat(id,heat);
        }
        model.addAttribute("book",book);
        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        List<Book> books1 = bookService.getAllBooks1();
        model.addAttribute("books1",books1);
        model.addAttribute("flag",0);
        return "subpage";
    }
}
