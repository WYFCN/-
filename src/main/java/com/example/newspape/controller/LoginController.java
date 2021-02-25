package com.example.newspape.controller;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Cart;
import com.example.newspape.bean.Order;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CartService;
import com.example.newspape.service.LoginService;
import com.example.newspape.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    CartService cartService;
    @Autowired
    LoginService loginService;
    @Autowired
    BookService bookService;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Autowired
    UserService userService;
    @ResponseBody
    @GetMapping("/toPra")
    public String ta(HttpServletRequest res)
    {
        System.out.println("##################");
        String loginUser = (String) res.getSession().getAttribute("loginUser");
        loginService.updateLogin(loginUser);
        return "succe";
    }
    @GetMapping(value = "/toCart/{id}")
    public String toCart(@PathVariable("id") Integer id, HttpServletRequest request, HttpSession session) {
        String username = (String) session.getAttribute("loginUser");
        boolean res = cartService.InsertCartByBookId(id, username);
        return "redirect:/toCart2";
    }
    @GetMapping(value = "/toCart2")
    public String toCart2(HttpServletRequest request, HttpSession session) {
        String username = (String) session.getAttribute("loginUser");
        List<Cart> cartList = cartService.cartListByUserName(username);
        session.setAttribute("cartLen",cartList.size());
        request.setAttribute("cartList", cartList);
        return "cart";
    }
    @ResponseBody
    @GetMapping(value = "/toDelCart")
    public boolean toDelCart(@RequestParam("cartId") int cartId){
        boolean res = cartService.delCartById(cartId);
        return res;
    }
    @ResponseBody
    @PostMapping(value = "/tosub_order")
    public String tosub_order(@RequestParam("cartIds[]") int[] cartIds,@RequestParam("nn[]") int[] nn,
                              @RequestParam("address") String address,HttpServletRequest res){
        System.out.println("……………………………………");
        System.out.println(address);
        System.out.println("……………………………………");
        List<Cart> cartList = cartService.cartList(cartIds);
        for (Cart cart : cartList) {
            System.out.println(cart.toString());
        }
        String loginUser = (String) res.getSession().getAttribute("loginUser");
        int id = userService.getId(loginUser);
        cartService.sub_Carts(cartList,nn,address,id);
        return "ok";
    }
}
