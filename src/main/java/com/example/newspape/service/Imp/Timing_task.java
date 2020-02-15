package com.example.newspape.service.Imp;

import com.example.newspape.bean.Order;
import com.example.newspape.service.LoginService;
import com.example.newspape.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class Timing_task {
    @Autowired
    OrderService orderService;
    @Autowired
    LoginService loginService;
    @Autowired
    HttpServletRequest res;
    @Scheduled(cron = "0 */1 * * * ?")
    public void updateOrders()
    {
        List<Order> orders = orderService.getAllOrders();
        for (Order order : orders) {
            if(order.getState().equals("成功发货"))
            {
                orderService.updateOrder04(order.getId());
            }
        }
    }
}
