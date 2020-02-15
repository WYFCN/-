package com.example.newspape.service.Imp;

import com.example.newspape.bean.Order;
import com.example.newspape.service.BookService;
import com.example.newspape.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Rabbitmq {
    private final static String flag_01 = "下单成功";
    private final static String flag_02 = "库存不足，下单失败";
    @Autowired
    BookService bookService;
    @Autowired
    OrderService orderService;

    @RabbitListener(queues = "Literature")
    public void Literature(Order order) throws Exception {
        String book_name = order.getName();
        int inventory = 0;
        inventory = bookService.getInventoryByName(book_name);
        System.out.println("库存余量--->" + inventory);
        if (inventory == 0) {
            orderService.updateOrder(order.getNum(), flag_02);
        } else if (inventory > 0) {
            bookService.updateInventory02(book_name);
            orderService.updateOrder(order.getNum(), flag_01);
            System.out.println("################");
        }
        Thread.sleep(1000);
    }

    @RabbitListener(queues = "Love")
    public void Love(Order order) {
        String book_name = order.getName();
        int inventory = bookService.getInventoryByName(book_name);
        if (inventory == 0) {
            orderService.updateOrder(order.getNum(), flag_02);
        } else if (inventory > 0) {
            bookService.updateInventory02(book_name);
            orderService.updateOrder(order.getNum(), flag_01);
        }
    }

    @RabbitListener(queues = "Philosophy")
    public void Philosophy(Order order) {
        String book_name = order.getName();
        int inventory = bookService.getInventoryByName(book_name);
        if (inventory == 0) {
            orderService.updateOrder(order.getNum(), flag_02);
        } else if (inventory > 0) {
            bookService.updateInventory02(book_name);
            orderService.updateOrder(order.getNum(), flag_01);
        }
    }

    @RabbitListener(queues = "Cartoon")
    public void Cartoon(Order order) {
        String book_name = order.getName();
        int inventory = bookService.getInventoryByName(book_name);
        if (inventory == 0) {
            orderService.updateOrder(order.getNum(), flag_02);
        } else if (inventory > 0) {
            bookService.updateInventory02(book_name);
            orderService.updateOrder(order.getNum(), flag_01);
        }
    }
}
