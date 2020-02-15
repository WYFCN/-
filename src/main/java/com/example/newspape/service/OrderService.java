package com.example.newspape.service;

import com.example.newspape.bean.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getOrders(Integer id);
    public void addOrder(Order order);
    public void updateOrder(String num,String s);
    public List<Order> getOrders02(String num);
    public List<Order> getAllOrders();
    public List<String> getNameByOr_User();
    public void deleteById(Integer id);
    public Order getOrderById(Integer id);
    public String getName(Integer userId);
    public void updateOrder02(Order order);
    public void updateOrder03(Integer id);
    public void updateOrder04(Integer id);
    public void updateOrder05(Integer id);
    public void updateOrder06(String num);
    public void update_p(String s,Integer id);
}
