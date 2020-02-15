package com.example.newspape.controller;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;
import com.example.newspape.bean.Order;
import com.example.newspape.bean.User;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import com.example.newspape.service.OrderService;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DetailsController {
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @GetMapping("/bookDetail")
    public String toBookDetail(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询用户列表 及设置分页信息
        List<Book> books = bookService.getAllBooks();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 3, books);
        model.addAttribute("books", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        return "bookDetail";
    }
    @GetMapping("/toCategory/{id}")
    public String toCategory(@PathVariable("id") Integer id, Model model,HttpServletRequest res)
    {
        List<Book> books2 = bookService.getAllBooks1();
        model.addAttribute("books1",books2);
        String category = categoryService.getBook(id);

        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);

        List<Book> books1 = bookService.getByCate(category);
        PageUtil pageInfo = new PageUtil(1, 4, books1);
        model.addAttribute("bookUtil", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        res.getSession().setAttribute("ff",1);
        res.getSession().setAttribute("cate",category);
        model.addAttribute("flag",0);
        return "list";
    }
    @GetMapping("/toBook/{id}")
    public String toBook(@PathVariable("id") Integer id,Model model)
    {
        Book book = bookService.getByBookId(id);
        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        List<Book> books1 = bookService.getAllBooks1();
        model.addAttribute("books1",books1);
        model.addAttribute("book",book);
        model.addAttribute("flag",0);
        return "saleBook";
    }
}
