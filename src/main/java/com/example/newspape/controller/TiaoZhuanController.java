package com.example.newspape.controller;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Category;
import com.example.newspape.bean.User;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TiaoZhuanController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable("id") Integer id,Model model)
    {
        User user = userService.getById(id);
        model.addAttribute("user",user);
        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        return "updateUser";
    }
    @PostMapping("/emp/{id}")
    public String deleteUser(@PathVariable("id") Integer id)
    {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @PostMapping("/emp/update")
    public String updateUser(User user){
        System.out.println(user);
        userService.updateUser(user);
        return "redirect:/users";
    }
    @GetMapping("/tolist")
    public String toList(Model model, @RequestParam Map<String, String> map, HttpServletRequest res)
    {
        List<Book> books1 = bookService.getAllBooks1();
        model.addAttribute("books1",books1);

        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询用户列表 及设置分页信息
        int ff= (int) res.getSession().getAttribute("ff");
        List<Book> books=new ArrayList<>();
        if(ff==1){
            String category= (String) res.getSession().getAttribute("cate");
            books = bookService.getByCate(category);
        }
        else if(ff==2)
        {
            books = bookService.getNewBook();
        }
        else if(ff==3)
        {
            String news = (String) res.getSession().getAttribute("keyWord");
            books = bookService.getByKeyword(news);
        }
        else if(ff==0)
        {
            books=bookService.getAllBooks();
        }
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 4, books);
        model.addAttribute("bookUtil", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);

        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        model.addAttribute("flag",1);
        return "list";
    }
    @GetMapping("/toNewRelease")
    public String toNewRelease(Model model,HttpServletRequest res)
    {
        List<Book> books1 = bookService.getAllBooks1();
        model.addAttribute("books1",books1);

        List<Book> books = bookService.getNewBook();
        PageUtil pageInfo = new PageUtil(1, 4, books);
        model.addAttribute("bookUtil", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        res.getSession().setAttribute("ff",2);

        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        model.addAttribute("flag",2);
        return "list";
    }
    @PostMapping("/searchBook")
    public String toSearchBook(@RequestParam("news") String news,Model model,HttpServletRequest res)
    {
        List<Book> books = bookService.getByKeyword(news);
        for (Book book : books) {
            System.out.println(book);
        }
        List<Book> books1 = bookService.getAllBooks1();
        model.addAttribute("books1",books1);
        List<Category> categorys = categoryService.getCategory();
        model.addAttribute("categorys",categorys);
        model.addAttribute("flag",3);
        if(books.size()!=0)
        {
//            System.out.println("############################################");
//            System.out.println("############################################");
////            model.addAttribute("books",books);
//            if(books.size()>=4)
//            {
//                model.addAttribute("books",books);
//                return "list";
//            }
//            else{
//                model.addAttribute("book", books.get(0));
//                return "saleBook";
//            }
            PageUtil pageInfo = new PageUtil(1, 4, books);
            model.addAttribute("bookUtil", pageInfo.getList());
            model.addAttribute("pageInfo", pageInfo);
            res.getSession().setAttribute("ff",3);
            res.getSession().setAttribute("keyWord",news);
            return "list";
        }
        else
            return "error";
    }
//    @ResponseBody
//    @GetMapping("/toSubscribe")
//    public Map<String, String> toSubscribe(String id){
//        int ID = Integer.parseInt(id);
//        int inventory = bookService.getInventoryById(ID);
//        Map<String,String>mp=new HashMap<>();
//        if(inventory>0){
//            bookService.updateInventory(ID);
//            mp.put("result","购买成功");
//        }
//        else
//            mp.put("result","库存不足，购买失败");
//        return mp;
//    }
}
