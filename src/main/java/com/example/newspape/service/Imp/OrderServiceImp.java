package com.example.newspape.service.Imp;

import com.example.newspape.bean.Order;
import com.example.newspape.mapper.OrderMapper;
import com.example.newspape.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> getOrders(Integer id) {
        return orderMapper.getOrders(id);
    }

    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public void updateOrder(String num, String s) {
        orderMapper.updateOrder(num,s);
    }

    @Override
    public List<Order> getOrders02(String num) {
        return orderMapper.getOrders02(num);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<String> getNameByOr_User() {
        return orderMapper.getNameByOr_User();
    }

    @Override
    public void deleteById(Integer id) {
        orderMapper.deleteById(id);
    }

    @Override
    public Order getOrderById(Integer id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public String getName(Integer userId) {
        return orderMapper.getName(userId);
    }

    @Override
    public void updateOrder02(Order order) {
        orderMapper.updateOrder02(order);
    }

    @Override
    public void updateOrder03(Integer id) {
        orderMapper.updateOrder03(id);
    }

    @Override
    public void updateOrder04(Integer id) {
        orderMapper.updateOrder04(id);
    }

    @Override
    public void updateOrder05(Integer id) {
        orderMapper.updateOrder05(id);
    }

    @Override
    public void updateOrder06(String num) {
        orderMapper.updateOrder06(num);
    }

    @Override
    public void update_p(String s, Integer id) {
        orderMapper.update_p(s,id);
    }
}
