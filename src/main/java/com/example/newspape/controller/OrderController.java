package com.example.newspape.controller;

import com.example.newspape.bean.Book;
import com.example.newspape.bean.Order;
import com.example.newspape.service.BookService;
import com.example.newspape.service.CategoryService;
import com.example.newspape.service.OrderService;
import com.example.newspape.service.UserService;
import com.example.newspape.util.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class OrderController {
    public static String Rout_key=new String();
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @GetMapping("/toOrder")
    public String toOrder(Model model, HttpServletRequest res,@RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        String username = (String) res.getSession().getAttribute("loginUser");
        System.out.println(username);
        int id = userService.getId(username);
        System.out.println(id);
        List<Order> orders = orderService.getOrders(id);
        for (Order order : orders) {
            System.out.println(order);
        }
        model.addAttribute("flag",4);
        //分页

        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 14, orders);
        model.addAttribute("orderUtil", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        System.out.println("####################");
        return "order_detail";
    }
    @GetMapping("/toSubscribe/{id}")
    public String toSubscribe(@PathVariable("id") Integer id,Model model,HttpServletRequest request)
    {
        request.getSession().setAttribute("bookId",id);
        System.out.println(id);
        Book book = bookService.getByBookId(id);
        model.addAttribute("book",book);
        int num= (int) (Math.random()*(9999999-1111111+1)+1111111);
        model.addAttribute("num",num);
        return "subscribe";
    }
    @PostMapping("/chuli_order")
    public String chuli_order(Order order,HttpServletRequest res)
    {
        int bookId = (int) res.getSession().getAttribute("bookId");
        String loginUser = (String) res.getSession().getAttribute("loginUser");
        System.out.println(order);
        int id = userService.getId(loginUser);
        order.setUserId(id);
        System.out.println(order);
        orderService.addOrder(order);
        String cate = categoryService.getCate(bookId);
        if(cate.equals("文学"))
            Rout_key=order.getName()+".literature";
        else if(cate.equals("爱情"))
            Rout_key=order.getName()+".love";
        else if(cate.equals("哲理"))
            Rout_key=order.getName()+".philosophy";
        else if(cate.equals("动漫"))
            Rout_key=order.getName()+".cartoon";
        //测试高并发下rabbit压力
//        for (int i = 1; i <=100; i++) {
//            order.setNum(String.valueOf(i));
//            orderService.addOrder(order);
//            String cate = categoryService.getCate(bookId);
//            if(cate.equals("文学"))
//                Rout_key=order.getName()+".literature";
//            else if(cate.equals("爱情"))
//                Rout_key=order.getName()+".love";
//            else if(cate.equals("哲理"))
//                Rout_key=order.getName()+".philosophy";
//            else if(cate.equals("动漫"))
//                Rout_key=order.getName()+".cartoon";
//            rabbitTemplate.convertAndSend("spring-boot-exchange",Rout_key,order);
//        }
        rabbitTemplate.convertAndSend("spring-boot-exchange",Rout_key,order);
        return "redirect:/toOrder";
    }
    @GetMapping("/orders")
    public String orders(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询订单列表 及设置分页信息
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 18, orders);
        model.addAttribute("ordersDTOList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        List<String> nameByOr_user = orderService.getNameByOr_User();
        for (String s : nameByOr_user) {
            System.out.println(s);
        }
        model.addAttribute("user_name",nameByOr_user);
        return "orderList";
    }
    @GetMapping("/orders_del")
    public String orders_del(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询订单列表 及设置分页信息
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 18, orders);
        model.addAttribute("ordersList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        List<String> nameByOr_user = orderService.getNameByOr_User();
        for (String s : nameByOr_user) {
            System.out.println(s);
        }
        model.addAttribute("user_name",nameByOr_user);
        return "order_delete";
    }
    @PostMapping("/order/{id}")
    public String order_del02(@PathVariable("id") Integer id)
    {
        System.out.println(id);
        orderService.deleteById(id);
        return "redirect:/orders_del";
    }
    @GetMapping("/orders_update")
    public String orders_update(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询订单列表 及设置分页信息
        List<Order> orders = orderService.getAllOrders();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 18, orders);
        model.addAttribute("ordersList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        List<String> nameByOr_user = orderService.getNameByOr_User();
        model.addAttribute("user_name",nameByOr_user);
        return "order_update";
    }
    @GetMapping("/update_order/{id}")
    public String update_order(@PathVariable("id") Integer id, Model model)
    {
        System.out.println(id);
        Order order = orderService.getOrderById(id);
        model.addAttribute("order",order);
        String name = orderService.getName(order.getUserId());
        model.addAttribute("name",name);
        return "update_order";
    }
    @PostMapping("/update_order")
    public String update_order02(Order order)
    {
        System.out.println(order);
        orderService.updateOrder02(order);
        return "redirect:/orders_update";
    }
    @GetMapping("/to_send_goods")
    public String to_send_goods(Model model, @RequestParam Map<String, String> map)
    {
        if (map.size() == 0 || map.get("currentPage").isEmpty()) {
            map.put("currentPage", "1");
        }
        // 查询订单列表 及设置分页信息
        List<Order> orders = orderService.getAllOrders();
        // 参数为当前页码、每页显示条数、查询的列表集合
        PageUtil pageInfo = new PageUtil(Integer.valueOf(map.get("currentPage")), 18, orders);
        model.addAttribute("ordersList", pageInfo.getList());
        model.addAttribute("pageInfo", pageInfo);
        List<String> nameByOr_user = orderService.getNameByOr_User();
        model.addAttribute("user_name",nameByOr_user);
        return "send_goods";
    }
    @PostMapping("/send_good/{id}")
    public String send_good(@PathVariable("id") Integer id, Model model)
    {
        orderService.updateOrder03(id);
        return "redirect:/to_send_goods";
    }
    @ResponseBody
    @GetMapping("/toShouHuo")
    public Map<String, String> toSubscribe(@RequestParam("id") String id){
        System.out.println(id);
        int anInt = Integer.parseInt(id);
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$");
        System.out.println(anInt);
        orderService.update_p("收货成功",anInt);
        Map<String,String>mp=new HashMap<>();
        mp.put("result","成功收货，欢迎再次购买！");
        return mp;
    }
}
