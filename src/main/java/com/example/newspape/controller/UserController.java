package com.example.newspape.controller;

import com.example.newspape.bean.*;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import com.example.newspape.service.LoginService;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    LoginService loginService;
    @GetMapping("/")
    public String tologin(){
        return "index";
    }
    @PostMapping("/yanzheng")
    public String yanzheng(User user, Model model,HttpServletRequest request){
        System.out.println(user);
        String s= userService.yanzheng(user.getUsername(), user.getPassword());
        System.out.println(s);
        if("success0".equals(s)){
//            List<Book> books = bookService.getAllBooks();
//            // 参数为当前页码、每页显示条数、查询的列表集合
//            PageUtil pageInfo = new PageUtil(1, 4, books);
//            model.addAttribute("bookUtil", pageInfo.getList());
//            model.addAttribute("pageInfo", pageInfo);
//            request.getSession().setAttribute("ff",0);
//
//            List<Book> books1 = bookService.getAllBooks1();
//            model.addAttribute("books1",books1);
//            List<Category> categorys = categoryService.getCategory();
//            model.addAttribute("categorys",categorys);
//            model.addAttribute("flag",1);
//            request.getSession().setAttribute("loginUser",user.getUsername());
//            request.getSession().setAttribute("USERNAME",userService.getName(user.getUsername()));
//
            Login login = loginService.getLogin(user.getUsername());
            System.out.println(login);
            if("null".equals(login))
            {
                //loginService.updateLogin02(user.getUsername());
                loginService.InsertLogin(user.getUsername(),"已登录");
                List<Book> books = bookService.getAllBooks();
                // 参数为当前页码、每页显示条数、查询的列表集合
                PageUtil pageInfo = new PageUtil(1, 4, books);
                model.addAttribute("bookUtil", pageInfo.getList());
                model.addAttribute("pageInfo", pageInfo);
                request.getSession().setAttribute("ff",0);

                List<Book> books1 = bookService.getAllBooks1();
                model.addAttribute("books1",books1);
                List<Category> categorys = categoryService.getCategory();
                model.addAttribute("categorys",categorys);
                model.addAttribute("flag",1);
                request.getSession().setAttribute("loginUser",user.getUsername());
                request.getSession().setAttribute("USERNAME",userService.getName(user.getUsername()));
                return "list";
            }else{
                String loginState = login.getLogin_state();
                System.out.println(loginState);
                if("未登录".equals(loginState))
                {
                    loginService.updateLogin02(user.getUsername());
                    List<Book> books = bookService.getAllBooks();
                    // 参数为当前页码、每页显示条数、查询的列表集合
                    PageUtil pageInfo = new PageUtil(1, 4, books);
                    model.addAttribute("bookUtil", pageInfo.getList());
                    model.addAttribute("pageInfo", pageInfo);
                    request.getSession().setAttribute("ff",0);

                    List<Book> books1 = bookService.getAllBooks1();
                    model.addAttribute("books1",books1);
                    List<Category> categorys = categoryService.getCategory();
                    model.addAttribute("categorys",categorys);
                    model.addAttribute("flag",1);
                    request.getSession().setAttribute("loginUser",user.getUsername());
                    request.getSession().setAttribute("USERNAME",userService.getName(user.getUsername()));
                    return "list";
                }else{
                    model.addAttribute("msg","已经登录，请勿在登录");
                    return "index";
                }
            }
        }
        else if("success1".equals(s))
        {
            request.getSession().setAttribute("loginUser",user.getUsername());
            return "adminList";
        }
        else
        {
            model.addAttribute("msg","密码错误,请重新输入");
            return "index";
        }
    }
    @GetMapping("/tozhuce")
    public String tozhaohui(){
        return "zhuce";
    }
    @PostMapping("/yanzhuce")
    public String yanzhuce(User user, Model model){
        System.out.println(user);
        Boolean judgeUsername = userService.judgeUsername(user.getUsername());
        if(judgeUsername)
        {
            userService.saveUser(user);
            String email=user.getEmail();
            SimpleMailMessage message = new SimpleMailMessage();
            String s="恭喜您注册成功，祝你使用愉快！！！";
            message.setSubject("注册成功");
            message.setText(s);
            message.setTo(email);
            message.setFrom("1270065107@qq.com");
            mailSender.send(message);
            return "index";
        }
        else
        {
            model.addAttribute("msgg","用户名已存在");
            return "zhuce";
        }
    }
}
