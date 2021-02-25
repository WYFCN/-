package com.example.newspape.service.Imp;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Cart;
import com.example.newspape.bean.Order;
import com.example.newspape.mapper.CartMapper;
import com.example.newspape.service.*;
import com.example.newspape.util.IDWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CartServiceImp implements CartService {
    public static String Rout_key=new String();
    @Autowired
    private BookService bookService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    CategoryService categoryService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Override
    public boolean InsertCartByBookId(Integer id,String username) {
        Book book = bookService.getByBookId(id);
        Cart cart = new Cart(book.getName(),book.getPress(),book.getDate(),
                book.getPrice(),book.getPrice(),1,book.getUrl(),username);
        boolean res = cartMapper.insertCart(cart);
        return res;
    }

    @Override
    public List<Cart> cartListByUserName(String username) {
        return cartMapper.cartListByUserName(username);
    }

    @Override
    public boolean delCartById(int id) {
        boolean res = cartMapper.delCartById(id);
        return res;
    }

    @Override
    public List<Cart> cartList(int[] ids) {
        List<Cart> cartList = cartMapper.cartList(ids);
        return cartList;
    }

    @Override
    public void sub_Carts(List<Cart> cartList, int[] nn,String address,int id) {
        System.out.println("****************");
        System.out.println(address);
        System.out.println("****************");
        for (int i = 0;i < cartList.size();i++) {
            Cart cart = cartList.get(i);
            Order order = new Order();
            order.setNum(String.valueOf(IDWorker.nextId()));
            order.setName(cart.getCart_name());
            order.setAmount(cart.getPrice()*nn[i]);
            order.setAddress(address);
            order.setUserId(id);
            orderService.addOrder(order);
            cartMapper.delCartById(cart.getId());
            Rout_key=order.getName()+".literature";
            rabbitTemplate.convertAndSend("spring-boot-exchange",Rout_key,order);
        }
    }
}
